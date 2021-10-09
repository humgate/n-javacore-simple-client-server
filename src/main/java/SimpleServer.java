import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
        public static void main(String[] args) {

            //запускаем сервер создав ServerSocket на порту 8090
            try (ServerSocket serverSocket = new ServerSocket(8090);

                 /* сим говорим серверу ждать клиентского подключения, и как только оно поступит
                  * сохранить его сокет соединения на серверной стороне в переменной clientSocket */
                 Socket clientSocket = serverSocket.accept();

                 //Текстовый писатель в поток вывода сокета соединения с клиентом
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                 //Буферизированный текстовый читатель из потока ввода сокета соединения с клиентом
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ) {
                System.out.println("Создано новое соединение");
                final String message = in.readLine();
                out.println("Сервер: Привет, клиент!, Твой порт соединения: " + clientSocket.getPort());
                out.println("Сервер: Получено сообщение: \"" + message + "\"");
                System.out.println("Получено сообщение: \"" + message + "\"");
              } catch (IOException e) {
                System.err.println("Ошибка соединения: " + e);
            }
        }
}
