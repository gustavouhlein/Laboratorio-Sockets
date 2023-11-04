import java.io.*;
import java.net.*;

class ClienteTCPCalculadora {
    public static void main(String argv[]) {
        String opcao;
        String respostaServidor;
        BufferedReader doUsuario = new BufferedReader(new InputStreamReader(System.in));

        try{
            Socket socketCliente = new Socket("localhost", 6789);
            DataOutputStream paraServidor = new DataOutputStream(socketCliente.getOutputStream());
            BufferedReader doServidor = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));

            while(true){

                System.out.println("Escolha uma das opções:");

                System.out.println("\t1. Soma\n\t2. Subtração\n\t3. Multiplicação\n\t4. Divisão\n\t5. Sair");
                opcao = doUsuario.readLine();

                paraServidor.writeBytes(opcao + '\n');

                if(opcao.equals("5"))
                    break;

                System.out.println("Digite o primeiro valor:");
                String valor1 = doUsuario.readLine();
                System.out.println("Digite o segundo valor:");
                String valor2 = doUsuario.readLine();

                paraServidor.writeBytes(valor1 + '\n');
                paraServidor.writeBytes(valor2 + '\n');

                System.out.println("Aguardando resposta do servidor...");

                respostaServidor = doServidor.readLine();

                System.out.println("Resultado: " + respostaServidor);
            }
            socketCliente.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}