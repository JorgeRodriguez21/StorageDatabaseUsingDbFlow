package uio.androidbootcamp.storagedatabaseusingdbflow.adapters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import uio.androidbootcamp.storagedatabaseusingdbflow.R;
import uio.androidbootcamp.storagedatabaseusingdbflow.models.User;

/**
 * Created by jrodri on 11/7/17.
 */

public class UsersRecyclerAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private Context context;
    private List<User> users;
    private View.OnClickListener listener;
    private final int VIEW_ITEM = 1;

    @Override
    public int getItemViewType(int position) {
        return VIEW_ITEM;
    }


    public UsersRecyclerAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public void onClick(View v) {
        if (listener != null)
            listener.onClick(v);
    }

    public void setClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private TextView state;
        private ConstraintLayout holderRL;

        private ViewHolder(View view) {
            super(view);
            state = (TextView) view.findViewById(R.id.text_view_name);
            holderRL = (ConstraintLayout) view.findViewById(R.id.holderCL);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        RecyclerView.ViewHolder vh;
        v = LayoutInflater.from(context).inflate(R.layout.users_item, parent, false);
        v.setOnClickListener(this);
        vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).state.setText(users.get(position).getName());
        ((ViewHolder) holder).holderRL.setTag(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

}
