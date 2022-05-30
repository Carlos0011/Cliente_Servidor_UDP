
import java.net.*;

public class ServidorUDP {
    public static void main(String[] args) throws Exception {
        //CRIAR SOCKET
        DatagramSocket tomadaServidora = new DatagramSocket(5000);

        while (true) {
            byte[] cartaAReceber = new byte[100];
            byte[] cartaAEnviar = new byte[100];
            System.out.println("\nServidor Aguardando...");

            //RECEBER MENSAGEM DO CLIENTE
            DatagramPacket envelopeAReceber = new DatagramPacket(cartaAReceber, cartaAReceber.length);
            tomadaServidora.receive(envelopeAReceber);
            String textoRecebido = new String(envelopeAReceber.getData());

            //INVERTER MENSAGEM
            StringBuilder textoInvertido = new StringBuilder(textoRecebido);
            textoInvertido = textoInvertido.reverse();
            String mensagem = textoInvertido.toString();

            //PRINTAR NA TELA
            System.out.println("\nRecebeu Mensagem: " + textoRecebido);
            System.out.println("Mensagem Enviada: " + mensagem);

            //ENVIAR MENSAGEM DE VOLTA AO CLIENTE
            cartaAEnviar = mensagem.getBytes();
            InetAddress ipCliente = envelopeAReceber.getAddress(); //obtém ip do cliente
            int portaCliente = envelopeAReceber.getPort(); //obtém porta do cliente
            DatagramPacket envelopeAEnviar = new DatagramPacket(cartaAEnviar,
                                                                cartaAEnviar.length,
                                                                ipCliente,
                                                                portaCliente);
            tomadaServidora.send(envelopeAEnviar);
        }
    }
}
