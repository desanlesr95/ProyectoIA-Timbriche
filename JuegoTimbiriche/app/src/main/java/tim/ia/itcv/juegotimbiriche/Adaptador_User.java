package tim.ia.itcv.juegotimbiriche;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luis on 16/11/17.
 */

public class Adaptador_User extends RecyclerView.Adapter<Adaptador_User.ViewHolder> implements View.OnClickListener{

    public List<User> users;
    Context context;
    Activity activity;

    public Adaptador_User(Activity activity) {
        this.activity=activity;
        users=new ArrayList();
    }

    public void add(List<User> users) {
        Log.i("usersNew", users.toString());
        this.users=users;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_layout,parent,false);
        context=parent.getContext();
        return new ViewHolder(v,activity);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.i("userpos", users.get(position).toString());
        holder.imagen.setImageResource(users.get(position).getImagen());
        holder.nombre.setText(users.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public void onClick(View view) {

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagen;
        public TextView nombre;
        public ViewHolder(View itemView,Activity activity) {
            super(itemView);
            imagen=(ImageView)itemView.findViewById(R.id.imgUser);
            nombre=(TextView) itemView.findViewById(R.id.nomUser);
        }


    }



}
