package tim.ia.itcv.juegotimbiriche;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONArray;

import java.net.URISyntaxException;

public class Cuadricula extends AppCompatActivity implements View.OnClickListener{
    boolean socketConnect=true;
    SocketIO socketIO;
    String nombre;
    String user1="";
    String user2;
    int p1=0,p2=0;
    String inicia;
    Socket mSocket;
    LinearLayout linearLayout;
    TextView imageView[];
    TextView j1,j2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuadricula);
        nombre=getIntent().getExtras().getString("nombre");
        user1=getIntent().getExtras().getString("user1");
        user2=getIntent().getExtras().getString("user2");
        inicia=getIntent().getExtras().getString("inicia");
        j1=(TextView)findViewById(R.id.j1);
        j2=(TextView)findViewById(R.id.j2);
        puntuacion();
        linearLayout=(LinearLayout)findViewById(R.id.cuadricula);
        conexion();
        imageView=new TextView[60];
        vistas();
        turno();
    }
    public void puntuacion(){
        j1.setText(user1+": "+p1);
        j2.setText(user2+": "+p2);
    }

    public void turno(){
        if (inicia.equals(nombre)){
            Toast.makeText(this,"Es tu turno",Toast.LENGTH_SHORT).show();
            linearLayout.setEnabled(true);
        }
        else{
            Toast.makeText(this,"Es el turno de "+inicia,Toast.LENGTH_SHORT).show();
            linearLayout.setEnabled(false);

        }
    }


    public void conexion(){
        socketIO=new SocketIO();
        mSocket=socketIO.getSocket();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setListening();
    }

    private void setListening() {
        mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d("task", "socket connected");
                socketConnect=true;
            }
        }).on("click-android", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                if(args[0].toString().equals(user1)&&args[1].toString().equals(user2)){
                    final int id=Integer.parseInt(args[3].toString());
                    final String turno_anterior=args[2].toString();
                    final TextView v = (TextView) findViewById(Integer.parseInt(args[3].toString()));
                    inicia=args[4].toString();
                    runOnUiThread(new Runnable() {
                        public void run() {
                            v.setBackgroundResource(R.color.colorPrimaryBlue);
                            v.setHint(" ");
                            checar(id,turno_anterior);
                            turno();
                        }
                    });
                }
            }
        }).on("usuarios", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.i("presencia",args[0].toString()+"");
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.i("desconectado","disconnect");
            }
        });
        mSocket.connect();
    }

    public void checar(int id,String turno){
        boolean ganado=false;
        if ((id==R.id.p1||id==R.id.p2||id==R.id.p3||id==R.id.p13)&&!ganado){
            TextView p[]=new TextView[4];
            p[0]=(TextView)findViewById(R.id.p1);
            p[1]=(TextView)findViewById(R.id.p2);
            p[2]=(TextView)findViewById(R.id.p3);
            p[3]=(TextView)findViewById(R.id.p13);

            int pun=0;
            for(int i=0;i<4;i++){
                if (!p[i].getHint().toString().equals("")) {
                    pun++;
                }
            }
            if (pun==4){
                ganado=true;
            }
            else{
                ganado = false;
            }

        }
        if ((id==R.id.p3||id==R.id.p4||id==R.id.p5||id==R.id.p15)&!ganado){
            TextView p[]=new TextView[4];
            p[0]=(TextView)findViewById(R.id.p3);
            p[1]=(TextView)findViewById(R.id.p4);
            p[2]=(TextView)findViewById(R.id.p5);
            p[3]=(TextView)findViewById(R.id.p15);
            int pun=0;
            for(int i=0;i<4;i++){
                if (!p[i].getHint().toString().equals("")) {
                    pun++;
                }
            }
            if (pun==4){
                ganado=true;
            }
            else{
                ganado = false;
            }

        }
        if ((id==R.id.p5||id==R.id.p6||id==R.id.p7||id==R.id.p17)&!ganado){
            TextView p[]=new TextView[4];
            p[0]=(TextView)findViewById(R.id.p5);
            p[1]=(TextView)findViewById(R.id.p6);
            p[2]=(TextView)findViewById(R.id.p7);
            p[3]=(TextView)findViewById(R.id.p17);

            int pun=0;
            for(int i=0;i<4;i++){
                if (!p[i].getHint().toString().equals("")) {
                    pun++;
                }
            }
            if (pun==4){
                ganado=true;
            }
            else{
                ganado = false;
            }
        }
        if ((id==R.id.p7||id==R.id.p8||id==R.id.p9||id==R.id.p19)&!ganado){
            TextView p[]=new TextView[4];
            p[0]=(TextView)findViewById(R.id.p7);
            p[1]=(TextView)findViewById(R.id.p8);
            p[2]=(TextView)findViewById(R.id.p9);
            p[3]=(TextView)findViewById(R.id.p19);

            int pun=0;
            for(int i=0;i<4;i++){
                if (!p[i].getHint().toString().equals("")) {
                    pun++;
                }
            }
            if (pun==4){
                ganado=true;
            }
            else{
                ganado = false;
            }

        }
        if ((id==R.id.p9||id==R.id.p10||id==R.id.p11||id==R.id.p21)&!ganado){
            TextView p[]=new TextView[4];
            p[0]=(TextView)findViewById(R.id.p9);
            p[1]=(TextView)findViewById(R.id.p10);
            p[2]=(TextView)findViewById(R.id.p11);
            p[3]=(TextView)findViewById(R.id.p21);

            int pun=0;
            for(int i=0;i<4;i++){
                if (!p[i].getHint().toString().equals("")) {
                    pun++;
                }
            }
            if (pun==4){
                ganado=true;
            }
            else{
                ganado = false;
            }

        }
        if ((id==R.id.p12||id==R.id.p13||id==R.id.p14||id==R.id.p24)&!ganado){
            TextView p[]=new TextView[4];
            p[0]=(TextView)findViewById(R.id.p12);
            p[1]=(TextView)findViewById(R.id.p13);
            p[2]=(TextView)findViewById(R.id.p14);
            p[3]=(TextView)findViewById(R.id.p24);

            int pun=0;
            for(int i=0;i<4;i++){
                if (!p[i].getHint().toString().equals("")) {
                    pun++;
                }
            }
            if (pun==4){
                ganado=true;
            }
            else{
                ganado = false;
            }

        }


        if ((id==R.id.p14||id==R.id.p15||id==R.id.p16||id==R.id.p26)&!ganado){
            TextView p[]=new TextView[4];
            p[0]=(TextView)findViewById(R.id.p14);
            p[1]=(TextView)findViewById(R.id.p15);
            p[2]=(TextView)findViewById(R.id.p16);
            p[3]=(TextView)findViewById(R.id.p26);

            int pun=0;
            for(int i=0;i<4;i++){
                if (!p[i].getHint().toString().equals("")) {
                    pun++;
                }
            }
            if (pun==4){
                ganado=true;
            }
            else{
                ganado = false;
            }

        }



        if ((id==R.id.p16||id==R.id.p17||id==R.id.p18||id==R.id.p28)&!ganado){
            TextView p[]=new TextView[4];
            p[0]=(TextView)findViewById(R.id.p16);
            p[1]=(TextView)findViewById(R.id.p17);
            p[2]=(TextView)findViewById(R.id.p18);
            p[3]=(TextView)findViewById(R.id.p28);

            int pun=0;
            for(int i=0;i<4;i++){
                if (!p[i].getHint().toString().equals("")) {
                    pun++;
                }
            }
            if (pun==4){
                ganado=true;
            }
            else{
                ganado = false;
            }

        }


        if ((id==R.id.p18||id==R.id.p19||id==R.id.p20||id==R.id.p30)&!ganado){
            TextView p[]=new TextView[4];
            p[0]=(TextView)findViewById(R.id.p18);
            p[1]=(TextView)findViewById(R.id.p19);
            p[2]=(TextView)findViewById(R.id.p20);
            p[3]=(TextView)findViewById(R.id.p30);

            int pun=0;
            for(int i=0;i<4;i++){
                if (!p[i].getHint().toString().equals("")) {
                    pun++;
                }
            }
            if (pun==4){
                ganado=true;
            }
            else{
                ganado = false;
            }

        }


        if ((id==R.id.p20||id==R.id.p21||id==R.id.p22||id==R.id.p32)&!ganado){
            TextView p[]=new TextView[4];
            p[0]=(TextView)findViewById(R.id.p20);
            p[1]=(TextView)findViewById(R.id.p21);
            p[2]=(TextView)findViewById(R.id.p22);
            p[3]=(TextView)findViewById(R.id.p32);

            int pun=0;
            for(int i=0;i<4;i++){
                if (!p[i].getHint().toString().equals("")) {
                    pun++;
                }
            }
            if (pun==4){
                ganado=true;
            }
            else{
                ganado = false;
            }

        }

        if ((id==R.id.p22||id==R.id.p23||id==R.id.p24||id==R.id.p34)&!ganado){
            TextView p[]=new TextView[4];
            p[0]=(TextView)findViewById(R.id.p22);
            p[1]=(TextView)findViewById(R.id.p23);
            p[2]=(TextView)findViewById(R.id.p24);
            p[3]=(TextView)findViewById(R.id.p34);

            int pun=0;
            for(int i=0;i<4;i++){
                if (!p[i].getHint().toString().equals("")) {
                    pun++;
                }
            }
            if (pun==4){
                ganado=true;
            }
            else{
                ganado = false;
            }

        }


        if ((id==R.id.p23||id==R.id.p24||id==R.id.p25||id==R.id.p35)&!ganado){
            TextView p[]=new TextView[4];
            p[0]=(TextView)findViewById(R.id.p23);
            p[1]=(TextView)findViewById(R.id.p24);
            p[2]=(TextView)findViewById(R.id.p25);
            p[3]=(TextView)findViewById(R.id.p35);

            int pun=0;
            for(int i=0;i<4;i++){
                if (!p[i].getHint().toString().equals("")) {
                    pun++;
                }
            }
            if (pun==4){
                ganado=true;
            }
            else{
                ganado = false;
            }

        }


        if ((id==R.id.p25||id==R.id.p26||id==R.id.p27||id==R.id.p37)&!ganado){
            TextView p[]=new TextView[4];
            p[0]=(TextView)findViewById(R.id.p25);
            p[1]=(TextView)findViewById(R.id.p26);
            p[2]=(TextView)findViewById(R.id.p27);
            p[3]=(TextView)findViewById(R.id.p37);

            int pun=0;
            for(int i=0;i<4;i++){
                if (!p[i].getHint().toString().equals("")) {
                    pun++;
                }
            }
            if (pun==4){
                ganado=true;
            }
            else{
                ganado = false;
            }

        }


        if ((id==R.id.p27||id==R.id.p28||id==R.id.p29||id==R.id.p39)&!ganado){
            TextView p[]=new TextView[4];
            p[0]=(TextView)findViewById(R.id.p27);
            p[1]=(TextView)findViewById(R.id.p28);
            p[2]=(TextView)findViewById(R.id.p29);
            p[3]=(TextView)findViewById(R.id.p39);

            int pun=0;
            for(int i=0;i<4;i++){
                if (!p[i].getHint().toString().equals("")) {
                    pun++;
                }
            }
            if (pun==4){
                ganado=true;
            }
            else{
                ganado = false;
            }

        }



        if ((id==R.id.p29||id==R.id.p30||id==R.id.p31||id==R.id.p41)&!ganado){
            TextView p[]=new TextView[4];
            p[0]=(TextView)findViewById(R.id.p29);
            p[1]=(TextView)findViewById(R.id.p30);
            p[2]=(TextView)findViewById(R.id.p31);
            p[3]=(TextView)findViewById(R.id.p41);

            int pun=0;
            for(int i=0;i<4;i++){
                if (!p[i].getHint().toString().equals("")) {
                    pun++;
                }
            }
            if (pun==4){
                ganado=true;
            }
            else{
                ganado = false;
            }

        }



        if ((id==R.id.p31||id==R.id.p32||id==R.id.p33||id==R.id.p43)&!ganado){
            TextView p[]=new TextView[4];
            p[0]=(TextView)findViewById(R.id.p31);
            p[1]=(TextView)findViewById(R.id.p32);
            p[2]=(TextView)findViewById(R.id.p33);
            p[3]=(TextView)findViewById(R.id.p43);

            int pun=0;
            for(int i=0;i<4;i++){
                if (!p[i].getHint().toString().equals("")) {
                    pun++;
                }
            }
            if (pun==4){
                ganado=true;
            }
            else{
                ganado = false;
            }

        }



        if ((id==R.id.p34||id==R.id.p35||id==R.id.p36||id==R.id.p46)&!ganado){
            TextView p[]=new TextView[4];
            p[0]=(TextView)findViewById(R.id.p34);
            p[1]=(TextView)findViewById(R.id.p35);
            p[2]=(TextView)findViewById(R.id.p36);
            p[3]=(TextView)findViewById(R.id.p46);

            int pun=0;
            for(int i=0;i<4;i++){
                if (!p[i].getHint().toString().equals("")) {
                    pun++;
                }
            }
            if (pun==4){
                ganado=true;
            }
            else{
                ganado = false;
            }

        }

        if ((id==R.id.p36||id==R.id.p37||id==R.id.p38||id==R.id.p48)&!ganado){
            TextView p[]=new TextView[4];
            p[0]=(TextView)findViewById(R.id.p36);
            p[1]=(TextView)findViewById(R.id.p37);
            p[2]=(TextView)findViewById(R.id.p38);
            p[3]=(TextView)findViewById(R.id.p48);

            int pun=0;
            for(int i=0;i<4;i++){
                if (!p[i].getHint().toString().equals("")) {
                    pun++;
                }
            }
            if (pun==4){
                ganado=true;
            }
            else{
                ganado = false;
            }

        }



        if ((id==R.id.p38||id==R.id.p39||id==R.id.p40||id==R.id.p50)&!ganado){
            TextView p[]=new TextView[4];
            p[0]=(TextView)findViewById(R.id.p38);
            p[1]=(TextView)findViewById(R.id.p39);
            p[2]=(TextView)findViewById(R.id.p40);
            p[3]=(TextView)findViewById(R.id.p50);

            int pun=0;
            for(int i=0;i<4;i++){
                if (!p[i].getHint().toString().equals("")) {
                    pun++;
                }
            }
            if (pun==4){
                ganado=true;
            }
            else{
                ganado = false;
            }

        }

        if ((id==R.id.p40||id==R.id.p41||id==R.id.p42||id==R.id.p52)&!ganado){
            TextView p[]=new TextView[4];
            p[0]=(TextView)findViewById(R.id.p40);
            p[1]=(TextView)findViewById(R.id.p41);
            p[2]=(TextView)findViewById(R.id.p42);
            p[3]=(TextView)findViewById(R.id.p52);

            int pun=0;
            for(int i=0;i<4;i++){
                if (!p[i].getHint().toString().equals("")) {
                    pun++;
                }
            }
            if (pun==4){
                ganado=true;
            }
            else{
                ganado = false;
            }

        }




        if ((id==R.id.p42||id==R.id.p43||id==R.id.p44||id==R.id.p54)&!ganado){
            TextView p[]=new TextView[4];
            p[0]=(TextView)findViewById(R.id.p42);
            p[1]=(TextView)findViewById(R.id.p43);
            p[2]=(TextView)findViewById(R.id.p44);
            p[3]=(TextView)findViewById(R.id.p54);

            int pun=0;
            for(int i=0;i<4;i++){
                if (!p[i].getHint().toString().equals("")) {
                    pun++;
                }
            }
            if (pun==4){
                ganado=true;
            }
            else{
                ganado = false;
            }

        }



        if ((id==R.id.p45||id==R.id.p46||id==R.id.p47||id==R.id.p56)&!ganado){
            TextView p[]=new TextView[4];
            p[0]=(TextView)findViewById(R.id.p45);
            p[1]=(TextView)findViewById(R.id.p46);
            p[2]=(TextView)findViewById(R.id.p47);
            p[3]=(TextView)findViewById(R.id.p56);

            int pun=0;
            for(int i=0;i<4;i++){
                if (!p[i].getHint().toString().equals("")) {
                    pun++;
                }
            }
            if (pun==4){
                ganado=true;
            }
            else{
                ganado = false;
            }

        }



        if ((id==R.id.p47||id==R.id.p48||id==R.id.p49||id==R.id.p57)&!ganado){
            TextView p[]=new TextView[4];
            p[0]=(TextView)findViewById(R.id.p47);
            p[1]=(TextView)findViewById(R.id.p48);
            p[2]=(TextView)findViewById(R.id.p49);
            p[3]=(TextView)findViewById(R.id.p57);

            int pun=0;
            for(int i=0;i<4;i++){
                if (!p[i].getHint().toString().equals("")) {
                    pun++;
                }
            }
            if (pun==4){
                ganado=true;
            }
            else{
                ganado = false;
            }

        }



        if ((id==R.id.p49||id==R.id.p50||id==R.id.p51||id==R.id.p58)&!ganado){
            TextView p[]=new TextView[4];
            p[0]=(TextView)findViewById(R.id.p49);
            p[1]=(TextView)findViewById(R.id.p50);
            p[2]=(TextView)findViewById(R.id.p51);
            p[3]=(TextView)findViewById(R.id.p58);

            int pun=0;
            for(int i=0;i<4;i++){
                if (!p[i].getHint().toString().equals("")) {
                    pun++;
                }
            }
            if (pun==4){
                ganado=true;
            }
            else{
                ganado = false;
            }

        }


        if ((id==R.id.p51||id==R.id.p52||id==R.id.p53||id==R.id.p59)&!ganado){
            TextView p[]=new TextView[4];
            p[0]=(TextView)findViewById(R.id.p51);
            p[1]=(TextView)findViewById(R.id.p52);
            p[2]=(TextView)findViewById(R.id.p53);
            p[3]=(TextView)findViewById(R.id.p59);

            int pun=0;
            for(int i=0;i<4;i++){
                if (!p[i].getHint().toString().equals("")) {
                    pun++;
                }
            }
            if (pun==4){
                ganado=true;
            }
            else{
                ganado = false;
            }

        }


        if ((id==R.id.p53||id==R.id.p54||id==R.id.p55||id==R.id.p60)&!ganado){
            TextView p[]=new TextView[4];
            p[0]=(TextView)findViewById(R.id.p53);
            p[1]=(TextView)findViewById(R.id.p54);
            p[2]=(TextView)findViewById(R.id.p55);
            p[3]=(TextView)findViewById(R.id.p60);

            int pun=0;
            for(int i=0;i<4;i++){
                if (!p[i].getHint().toString().equals("")) {
                    pun++;
                }
            }
            if (pun==4){
                ganado=true;
            }
            else{
                ganado = false;
            }

        }


        if (ganado){
            if (turno.equals(user1)){
                p1++;
            }
            else{
                p2++;
            }
        }
        puntuacion();
        if ((p1+p2)==25){
            if (p1<p2){
                new MaterialDialog.Builder(Cuadricula.this)
                        .title("Ganador")
                        .content("El ganador es "+user2+" con "+p2+" puntos")
                        .positiveText("Aceptar")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog,
                                                @NonNull DialogAction which) {

                            }
                        }).build().show();
            }
            else{
                new MaterialDialog.Builder(Cuadricula.this)
                        .title("Ganador")
                        .content("El ganador es "+user1+" con "+p1+" puntos")
                        .positiveText("Aceptar")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog,
                                                @NonNull DialogAction which) {

                            }
                        }).build().show();
            }
        }
    }


    public void vistas(){
        imageView[0]=(TextView)findViewById(R.id.p1);
        imageView[1]=(TextView)findViewById(R.id.p2);
        imageView[2]=(TextView)findViewById(R.id.p3);
        imageView[3]=(TextView)findViewById(R.id.p4);
        imageView[4]=(TextView)findViewById(R.id.p5);
        imageView[5]=(TextView)findViewById(R.id.p6);
        imageView[6]=(TextView)findViewById(R.id.p7);
        imageView[7]=(TextView)findViewById(R.id.p8);
        imageView[8]=(TextView)findViewById(R.id.p9);
        imageView[9]=(TextView)findViewById(R.id.p10);
        imageView[10]=(TextView)findViewById(R.id.p11);
        imageView[11]=(TextView)findViewById(R.id.p12);
        imageView[12]=(TextView)findViewById(R.id.p13);
        imageView[13]=(TextView)findViewById(R.id.p14);
        imageView[14]=(TextView)findViewById(R.id.p15);
        imageView[15]=(TextView)findViewById(R.id.p16);
        imageView[16]=(TextView)findViewById(R.id.p17);
        imageView[17]=(TextView)findViewById(R.id.p18);
        imageView[18]=(TextView)findViewById(R.id.p19);
        imageView[19]=(TextView)findViewById(R.id.p20);
        imageView[20]=(TextView)findViewById(R.id.p21);
        imageView[21]=(TextView)findViewById(R.id.p22);
        imageView[22]=(TextView)findViewById(R.id.p23);
        imageView[23]=(TextView)findViewById(R.id.p24);
        imageView[24]=(TextView)findViewById(R.id.p25);
        imageView[25]=(TextView)findViewById(R.id.p26);
        imageView[26]=(TextView)findViewById(R.id.p27);
        imageView[27]=(TextView)findViewById(R.id.p28);
        imageView[28]=(TextView)findViewById(R.id.p29);
        imageView[29]=(TextView)findViewById(R.id.p30);
        imageView[30]=(TextView)findViewById(R.id.p31);
        imageView[31]=(TextView)findViewById(R.id.p32);
        imageView[32]=(TextView)findViewById(R.id.p33);
        imageView[33]=(TextView)findViewById(R.id.p34);
        imageView[34]=(TextView)findViewById(R.id.p35);
        imageView[35]=(TextView)findViewById(R.id.p36);
        imageView[36]=(TextView)findViewById(R.id.p37);
        imageView[37]=(TextView)findViewById(R.id.p38);
        imageView[38]=(TextView)findViewById(R.id.p39);
        imageView[39]=(TextView)findViewById(R.id.p40);
        imageView[40]=(TextView)findViewById(R.id.p41);
        imageView[41]=(TextView)findViewById(R.id.p42);
        imageView[42]=(TextView)findViewById(R.id.p43);
        imageView[43]=(TextView)findViewById(R.id.p44);
        imageView[44]=(TextView)findViewById(R.id.p45);
        imageView[45]=(TextView)findViewById(R.id.p46);
        imageView[46]=(TextView)findViewById(R.id.p47);
        imageView[47]=(TextView)findViewById(R.id.p48);
        imageView[48]=(TextView)findViewById(R.id.p49);
        imageView[49]=(TextView)findViewById(R.id.p50);
        imageView[50]=(TextView)findViewById(R.id.p51);
        imageView[51]=(TextView)findViewById(R.id.p52);
        imageView[52]=(TextView)findViewById(R.id.p53);
        imageView[53]=(TextView)findViewById(R.id.p54);
        imageView[54]=(TextView)findViewById(R.id.p55);
        imageView[55]=(TextView)findViewById(R.id.p56);
        imageView[56]=(TextView)findViewById(R.id.p57);
        imageView[57]=(TextView)findViewById(R.id.p58);
        imageView[58]=(TextView)findViewById(R.id.p59);
        imageView[59]=(TextView)findViewById(R.id.p60);
        for(int i=0;i<60;i++){
            imageView[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        //intentar convertor el view en imageview
        if (inicia.equals(nombre)){
            TextView v = (TextView) findViewById(view.getId());
            if(v.getHint().equals("")) {
                Log.i("si", "si");
                mSocket.emit("click",user1,user2,inicia,view.getId(),"falso");
            }
            else {
                Toast.makeText(getApplicationContext(), "Punto ya seleccionado", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"Espera tu turno por favor :)",Toast.LENGTH_LONG).show();
        }

    }


}