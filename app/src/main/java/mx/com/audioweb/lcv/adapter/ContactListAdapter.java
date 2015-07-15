package mx.com.audioweb.lcv.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import mx.com.audioweb.lcv.R;
import mx.com.audioweb.lcv.data.Contacto;

/**
 * Created by Juan Acosta on 8/19/2014.
 */
public class ContactListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Contact_Item> contact_items;


    public ContactListAdapter(Context context, ArrayList<Contact_Item> contact_items) {
        this.context = context;
        this.contact_items = contact_items;
    }

    public int getCount() {
        return this.contact_items.size();
    }

    public Object getItem(int position) {
        return this.contact_items.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.contactos_list, null);
        }

        TextView Nombre = (TextView) convertView.findViewById(R.id.Nombre);
        TextView Telefono = (TextView) convertView.findViewById(R.id.Telefono);
        TextView Correo = (TextView) convertView.findViewById(R.id.Correo);

        Nombre.setText(contact_items.get(position).getNombre());
        Telefono.setText(contact_items.get(position).getTelefono());
        Correo.setText(contact_items.get(position).getCorreo());

        return convertView;
    }
}
