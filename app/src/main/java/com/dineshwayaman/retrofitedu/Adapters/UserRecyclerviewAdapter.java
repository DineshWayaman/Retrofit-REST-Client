package com.dineshwayaman.retrofitedu.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dineshwayaman.retrofitedu.Models.Users;
import com.dineshwayaman.retrofitedu.R;

import java.util.List;

public class UserRecyclerviewAdapter extends RecyclerView.Adapter<UserRecyclerviewAdapter.UserViewHoleder> {

//    List to hold user data
    private List<Users> usersList;

//    constructor to init the user list
    public UserRecyclerviewAdapter(List<Users> users){
        this.usersList = users;
    }

    @NonNull
    @Override
    public UserRecyclerviewAdapter.UserViewHoleder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//       inflate the layout for each item of the recyclerview
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_details_item, parent, false);
        return new UserViewHoleder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserRecyclerviewAdapter.UserViewHoleder holder, int position) {
//        get user data at the current position and bind data in to the view
            Users userModel = usersList.get(position);

//            set the data in to the textview
            holder.txtID.setText(String.valueOf(userModel.getId()));
            holder.txtName.setText(userModel.getName());
            holder.txtEmail.setText(userModel.getEmail());
            holder.txtOthers.setText(userModel.getAddress().getCity());

    }

    @Override
    public int getItemCount() {
//        return the size of user list
        return usersList.size();
    }

    public class UserViewHoleder extends RecyclerView.ViewHolder {
//       declare textview variables
        TextView txtID, txtName, txtEmail, txtOthers;

//        Constructor to initialize the views
        public UserViewHoleder(@NonNull View itemView) {
            super(itemView);
            txtID = itemView.findViewById(R.id.txtID);
            txtName = itemView.findViewById(R.id.txtName);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            txtOthers = itemView.findViewById(R.id.txtOthers);
        }
    }
}
