package org.example;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

class Person {
    public Integer id;
    public String name, company, username, email, address,
            zip, state, country, phone, photo;
    public void print()
    {
        System.out.println("id: " + this.id + "\n name: " + this.name+"\n company: " +
                this.company + "\n username: " + this.username + "\n email: " + this.email
                + "\n address: " + this.address + "\n zip: " + this.zip + "\n state: " + this.state
                + "\n country: " + this.country + "\n phone: " + this.phone + "\n photo: " +
                this.photo + "\n");
    }
}

class user_tasks {
    public Integer userId, id;
    public String title;
    public boolean completed;
    public void print()
    {
        System.out.println("userId: " + this.userId + "\nid: " +  this.id
                + "\ntitle: " + this.title + "\ncompleted: " + this.completed + "\n");
    }
}


public class Main {
    public static void main(String[] args) {
        try {
            URL url_1 = new URL("https://fake-json-api.mock.beeceptor.com/users");
            URL url_2 = new URL("https://dummy-json.mock.beeceptor.com/todos");
            try (InputStream in = url_1.openStream();
                 InputStreamReader byte_trans = new InputStreamReader(in);
                 BufferedReader reader = new BufferedReader(byte_trans)) {

                StringBuilder json_storage = new StringBuilder();
                String parse_line;
                while ((parse_line = reader.readLine()) != null) {
                    json_storage.append(parse_line);
                }

                Gson gson = new Gson();
                Person[] people = gson.fromJson(json_storage.toString(), Person[].class);

                for (Person person : people) {
                    person.print();
                }
            }
            try (InputStream in = url_2.openStream();
                 InputStreamReader byte_trans = new InputStreamReader(in);
                 BufferedReader reader = new BufferedReader(byte_trans)) {

                StringBuilder json_storage = new StringBuilder();
                String parse_line;
                while ((parse_line = reader.readLine()) != null) {
                    json_storage.append(parse_line);
                }
                Gson gson = new Gson();
                user_tasks[] tasks = gson.fromJson(json_storage.toString(), user_tasks[].class);
                for (user_tasks task : tasks) {
                    task.print();
                }
            }
            
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}