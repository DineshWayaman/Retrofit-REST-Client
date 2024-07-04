Retrofit

Retrofit Simple Fetch

## Tech Stack

Native Android Java


## Usage/Examples



```java

Dependancies
com.squareup.retrofit2:retrofit:2.9.0
com.squareup.retrofit2:converter-gson:2.9.0


<!-- Activity -->
init recyclerview and set it's layout
recyclerUser = findViewById(R.id.recyclerUser);
recyclerUser.setLayoutManager(new LinearLayoutManager(this));

// methode to handle fetch data
    private void fetchData() {
//        create retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//        create instance of the API service
        ApiService apiService = retrofit.create(ApiService.class);

//        make an asyn call to fetch the users
        apiService.getUsers().enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
//                check if the response is success and the body is not null
                if(response.isSuccessful() && response.body() != null){
//                    init adapter with the list of users and set it to the recyclerview
                    userRecyclerviewAdapter = new UserRecyclerviewAdapter(response.body());
                    recyclerUser.setAdapter(userRecyclerviewAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
// if failed to fetch data
                Toast.makeText(MainActivity.this, "Failed to fetch data: " + t, Toast.LENGTH_SHORT).show();
            }
        });

    }


    // Adapter

    private List<Users> usersList;

//    constructor to init the user list
    public UserRecyclerviewAdapter(List<Users> users){
        this.usersList = users;
    }
    

    // onCreateViewHolder

    //       inflate the layout for each item of the recyclerview
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_details_item, parent, false);
        return new UserViewHoleder(view);


        // onBindViewHolder

        //        get user data at the current position and bind data in to the view
            Users userModel = usersList.get(position);

//            set the data in to the textview
            holder.txtID.setText(String.valueOf(userModel.getId()));
            holder.txtName.setText(userModel.getName());
            holder.txtEmail.setText(userModel.getEmail());
            holder.txtOthers.setText(userModel.getAddress().getCity());



            // ViewHoleder

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


<!-- Services -->

    @GET("users")
    Call<List<Users>> getUsers();

<!-- Model -->

public class Users {
    private int id;
    private String name;
    private String email;
    private Address address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public static class Address{
        private String street;
        private String suite;

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getSuite() {
            return suite;
        }

        public void setSuite(String suite) {
            this.suite = suite;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public Geo getGeo() {
            return geo;
        }

        public void setGeo(Geo geo) {
            this.geo = geo;
        }

        private String city;
        private String zipcode;
        private Geo geo;

        public static class Geo{
            private String lat;

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            private String lng;
        }

    }

}

<!-- Add recyclerview to activity -->
 <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recyclerUser"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        />


<!-- item -->

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <TextView
        android:id="@+id/txtID"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="20.0sp"/>

    <TextView
        android:id="@+id/txtName"
        android:layout_below="@id/txtID"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="20.0sp"/>

    <TextView
        android:id="@+id/txtEmail"
        android:layout_below="@id/txtName"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="20.0sp"/>

    <TextView
        android:id="@+id/txtOthers"
        android:layout_below="@id/txtEmail"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="20.0sp"/>

</RelativeLayout>

```

