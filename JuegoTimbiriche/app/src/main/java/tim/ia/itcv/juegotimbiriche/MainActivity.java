package tim.ia.itcv.juegotimbiriche;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText user;
    Button aceptar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=(EditText)findViewById(R.id.user);
        aceptar=(Button) findViewById(R.id.aceptar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Introduce un nombre",Toast.LENGTH_LONG).show();
                }
                else{
                    startActivity(new Intent(getApplicationContext(),Elegir_users.class).putExtra("nombre",user.getText().toString()));
                    //startActivity(new Intent(getApplicationContext(),Cuadricula.class).putExtra("nombre",user.getText().toString()));
                    finish();
                }
            }
        });
    }
}
