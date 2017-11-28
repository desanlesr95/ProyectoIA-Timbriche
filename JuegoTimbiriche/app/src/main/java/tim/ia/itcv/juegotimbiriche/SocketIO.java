package tim.ia.itcv.juegotimbiriche;

import android.util.Log;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;



public class SocketIO {
    boolean conn=false;
    private static final String TAG = "Socket";
    Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://192.168.0.6:3000/");
            conn=true;
        } catch (URISyntaxException e) {
            Log.d(TAG, "error connect socket " + e.getMessage());
            conn=false;
        }
    }

    public Socket getSocket(){
        return mSocket;
    }

    public Boolean isConnected(){
        return conn;
    }

}