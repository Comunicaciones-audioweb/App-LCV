package mx.com.audioweb.lcv.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

import mx.com.audioweb.lcv.R;
import mx.com.audioweb.lcv.data.Participante;

/**
 * Created by Juan Acosta on 8/21/2014.
 */
public class ParticipanteListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Participante_Item> participante_items;

    public ParticipanteListAdapter(Context context, ArrayList<Participante_Item> participante_items) {
        this.context = context;
        this.participante_items = participante_items;
    }

    public int getCount() {
        return this.participante_items.size();
    }

    public Object getItem(int position) {
        return this.participante_items.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.lcv_list, null);
        }

        TextView Nombre = (TextView) convertView.findViewById(R.id.mi_name);
        TextView Id = (TextView) convertView.findViewById(R.id.mi_id);
        TextView Time = (TextView) convertView.findViewById(R.id.mi_join_time);

        Nombre.setText(participante_items.get(position).getName());
        Id.setText(participante_items.get(position).getId());
        Time.setText(participante_items.get(position).getTime());

        return convertView;
    }
}
