package hateoas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestGSON {
    public static void main(String[] args) {
        Albums albums = new Albums();
        albums.title = "Free Music Archive - Albums";
        albums.message = "";
        albums.total = "11259";
        albums.total_pages = 2252;
        albums.page = 1;
        albums.limit = "5";
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        System.out.println(gson.toJson(albums));
    }
}

class Albums {
    public String title;
    public String message;
    public String[] errors = new String[]{};
    public String total;
    public int total_pages;
    public int page;
    public String limit;
}
