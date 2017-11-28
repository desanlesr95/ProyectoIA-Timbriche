package tim.ia.itcv.juegotimbiriche;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Elegir_users extends AppCompatActivity {
    SocketIO socketIO;
    String nombre;
    Socket mSocket;
    ListView listusers;
    String users[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_users);
        nombre=getIntent().getExtras().getString("nombre");
        listusers=(ListView)findViewById(R.id.list_users);
        conexion();
    }

    public void conexion(){
        socketIO=new SocketIO();
        mSocket=socketIO.getSocket();
        setListening();
    }

    private void setListening() {
        mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d("elegir_users", "socket connected");
            }
        }).on("play", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.i("args",args[0].toString());
                Log.i("args",args[1].toString());
                Log.i("args",args[2].toString());
                if (args[0].toString().equals(nombre) || args[1].toString().equals(nombre)){
                    startActivity(new Intent(getApplicationContext(),Cuadricula.class).
                            putExtra("user1",args[0].toString()).
                            putExtra("user2",args[1].toString()).
                            putExtra("inicia",args[2].toString()).
                            putExtra("nombre",nombre)
                    );
                    finish();
                }

            }
        }).on("usuarios", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.i("users", args[0].toString() + "");
                try {
                    JSONArray array=new JSONArray(args[0].toString());

                    users=new String[array.length()];
                    for (int i=0;i<array.length();i++) {
                        users[i] = array.getString(i);
                    }
                    final ArrayAdapter  adapter =new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,users);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listusers.setAdapter(adapter);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).on("precencia", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.i("precencia",args[0].toString());
            }
        }).on("peticion play", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                Log.i("args",args[0].toString());
                Log.i("args",args[1].toString());
                if (args[1].toString().equals(nombre)){
                    //vetanita
                    runOnUiThread(new Runnable() {
                        public void run() {
                            new MaterialDialog.Builder(Elegir_users.this)
                                    .title("Solicitud para jugar")
                                    .content("Quieres jugar con"+args[0].toString())
                                    .negativeText("Cancelar")
                                    .positiveText("Aceptar")
                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog,
                                                            @NonNull DialogAction which) {
                                            mSocket.emit("intent ok",nombre,args[0].toString());
                                        }
                                    }).onNegative(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        mSocket.emit("intent cancel",nombre,args[0].toString());
                                    }
                                }).build().show();
                        }
                    });


                }
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.i("desconectado","disconnect");
            }
        });
        mSocket.connect();
        mSocket.emit("precencia",nombre,R.mipmap.ic_launcher);

        listusers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("seleccionado",users[i]);
                mSocket.emit("intent game",nombre,users[i]);
            }
        });

    }

}
