
import java.net.*;
import java.util.*;

public class ClienteUDP {

    public static void main(String args[]) throws Exception {
		
        //CRIAR SOCKET
		DatagramSocket tomada = new DatagramSocket();
        
		//OBTÉM INPUT
        System.out.print("\nDigite uma Mensagem: ");
        Scanner teclado = new Scanner(System.in);
        String mensagem = teclado.nextLine();
        
		//CÓDIGO PARA ENVIAR UMA MENSAGEM AO SERVIDOR
        byte[] cartaAEnviar = new byte[100];
        cartaAEnviar = mensagem.getBytes();
        InetAddress ip = InetAddress.getByName("127.0.0.1");
        DatagramPacket envelopeAEnviar = new DatagramPacket(cartaAEnviar,
                                                            cartaAEnviar.length,
                                                            ip,
                                                            5000);                                     
        tomada.send(envelopeAEnviar);

        //CÓDIGO PARA RECEBER UMA MENSAGEM DO SERVIDOR
        byte[] cartaAReceber = new byte[100];
        DatagramPacket envelopeAReceber = new DatagramPacket(cartaAReceber,
                                                             cartaAReceber.length);
        tomada.receive(envelopeAReceber);
        String mensagemRecebida = new String(envelopeAReceber.getData());
        System.out.println("\nMensagem Invertida: " + mensagemRecebida + "\n");

        //FINALIZAR CONEXÃO
        teclado.close();
        tomada.close();
    }
}
