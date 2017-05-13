import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket; //class Socket is in java.net

/**
 * Created by Саша on 05.05.2017.
 */
public class DailyAdviceClient {
    public static void main(String[] args) {
        DailyAdviceClient dailyAdviceClient = new DailyAdviceClient();
        dailyAdviceClient.go();
    }

    private void go() {
        try{ //a lot can go wrong here
            Socket socket = new Socket("127.0.0.1", 4242);

            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String advice = reader.readLine();
            System.out.println("Today you should: " + advice);

            reader.close(); //this close ALL the streams
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
