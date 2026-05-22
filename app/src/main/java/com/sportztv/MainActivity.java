package com.sportztv;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.content.Intent;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Channel> channels = ChannelData.getChannels();
        List<String> names = new ArrayList<>();
        for (Channel ch : channels) {
            names.add(ch.category + " - " + ch.name);
        }

        ListView listView = findViewById(R.id.channelList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, names);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Channel ch = channels.get(position);
            try {
                String url = CryptoHelper.decrypt(ch.encryptedUrl);
                String referer = CryptoHelper.decrypt(ch.encryptedReferer);
                String origin = CryptoHelper.decrypt(ch.encryptedOrigin);
                String userAgent = CryptoHelper.decrypt(ch.encryptedUserAgent);

                Intent intent = new Intent(this, PlayerActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("referer", referer);
                intent.putExtra("origin", origin);
                intent.putExtra("userAgent", userAgent);
                intent.putExtra("name", ch.name);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, "خطأ في تشغيل القناة", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
