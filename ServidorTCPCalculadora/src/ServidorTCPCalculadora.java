import java.io.*;
import java.net.*;

class ServidorTCPCalculadora{
    public static void main(String args[]) {
        String opcaoCliente;
        ServerSocket socketRecepcao = null;
        try{
            socketRecepcao = new ServerSocket(6789);

            while(true) {
                System.out.println("Servidor aguardando conexão...");
                Socket socketConexao = socketRecepcao.accept();

                System.out.println("Estabelecendo conexão...");

                BufferedReader doCliente = new BufferedReader(new InputStreamReader(socketConexao.getInputStream()));
                DataOutputStream paraCliente = new DataOutputStream(socketConexao.getOutputStream());

                while(true){

                    System.out.println("Aguardando cliente...");

                    opcaoCliente = doCliente.readLine();

                    if(opcaoCliente.equals("5"))
                        break;

                    double valor1 = Double.parseDouble(doCliente.readLine());
                    double valor2 = Double.parseDouble(doCliente.readLine());

                    System.out.println("Opção do cliente: ["+opcaoCliente+"]");

                    switch(opcaoCliente){
                        case "1":
                            System.out.println("Somando "+valor1+" e "+valor2);
                            paraCliente.writeBytes((valor1+valor2)+"\n");
                            break;
                        case "2":
                            System.out.println("Subraindo "+valor2+" de "+valor1);
                            paraCliente.writeBytes((valor1-valor2)+"\n");
                            break;
                        case "3":
                            System.out.println("Multiplicando "+valor1+" por "+valor2);
                            paraCliente.writeBytes((valor1*valor2)+"\n");
                            break;
                        case "4":
                            System.out.println("Dividindo "+valor1+" por "+valor2);
                            paraCliente.writeBytes((valor1/valor2)+"\n");
                            break;
                    }
                }
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}