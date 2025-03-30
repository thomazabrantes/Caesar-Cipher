/**
 * @author Thomaz Abrantes
 * @author Pedro Soares
 */
public class App {

    // linha para dividir e separar as saídas do software
    public static void linhaDivisoria() {
        System.out.println(
                "-------------------------------------------------------------------------------------------------------------");
    }

    /**
     * Método que realiza o cálculo do deslocamento com base na matrícula e no
     * dígito verificador
     * 
     * @param matricula
     * @param digito
     * @return o deslocamento encontrado
     */
    public static int calculaDeslocamento(int matricula, int digito) {
        int deslocamento;
        deslocamento = (matricula / (digito + 1)) % 26;
        return deslocamento;
    }

    /**
     * Método que recebe a frase criptografada e realiza a decriptografia
     * 
     * @param frase
     * @param deslocamento
     * @return a frase descriptografada
     */
    public static String descriptografar(String frase, int deslocamento) {
        String fraseDescriptografada = "";
        for (int i = 0; i < frase.length(); i++) {
            char letra = frase.charAt(i);
            int valor = (int) letra;
            if ((valor - deslocamento) >= 0x61) {
                valor -= deslocamento;
                letra = (char) valor;
                fraseDescriptografada = fraseDescriptografada + letra;
            } else {
                int numero = 0x1a - deslocamento;
                valor += numero;
                letra = (char) valor;
                fraseDescriptografada = fraseDescriptografada + letra;
            }
        }
        return fraseDescriptografada;
    }

    /**
     * Método que recebe a frase descriptografada e realiza a criptografia
     * 
     * @param frase
     * @param deslocamento
     * @return a frase criptografada
     */
    public static String criptografar(String frase, int deslocamento) {
        String fraseCriptografada = "";
        for (int i = 0; i < frase.length(); i++) {
            char letra = frase.charAt(i);
            int valor = (int) letra;
            if ((valor + deslocamento) <= 0x7a) {
                valor += deslocamento;
                letra = (char) valor;
                fraseCriptografada = fraseCriptografada + letra;
            } else {
                int numero = 0x1a - deslocamento;
                valor -= numero;
                letra = (char) valor;
                fraseCriptografada = fraseCriptografada + letra;
            }
        }
        return fraseCriptografada;
    }

    public static void main(String[] args) throws Exception {
        linhaDivisoria();
        System.out.println("Olá usuário, seja bem vindo!");
        System.out.println("Esse código consegue descriptografar um código feito a partir de uma Cifra de César.");
        System.out.println(
                "O deslocamento da Cifra é calculado através do seguinte cálculo: (matrícula / (digito verificador + 1)) % 26");
        linhaDivisoria();
        // matricula e digito thomaz
        int matricula = 23102711;
        System.out.println("THOMAZ:");
        System.out.printf("Matricula: %d \n", matricula);
        int digito = 1;
        System.out.printf("Dígito verificador: %d \n", digito);
        // chama o método de calcular o deslocamento
        int deslocamento = calculaDeslocamento(matricula, digito);
        System.out.printf("O cálculo do deslocamento resulta em: %d. \n", deslocamento);
        // frase criptografada thomaz
        /*
         * 0x6c, 0x6a, 0x62, 0x72, 0x7a, 0x6c, 0x6a, 0x6d, 0x66, 0x69, 0x78, 0x61, 0x6c,
         * 0x6f, 0x62, 0x70, 0x71, 0x78, 0x70, 0x62, 0x6a,
         * 0x6d, 0x6f, 0x62, 0x61, 0x66, 0x77, 0x62, 0x6b, 0x61, 0x6c, 0x6e, 0x72, 0x62,
         * 0x6a, 0x62, 0x72, 0x7a, 0x6c, 0x61, 0x66, 0x64, 0x6c,
         * 0x62, 0x70, 0x71, 0x78, 0x79, 0x78, 0x66, 0x75, 0x78, 0x6b, 0x61, 0x6c, 0x6c,
         * 0x6b, 0x66, 0x73, 0x62, 0x69
         */
        // transformando em letras do alfabeto através da tabela ASCII
        String frase = "ljbrzljmfixalobpqxpbjmobafwbkalnrbjbrzlafdlbpqxyxfuxkallkfsbi";
        System.out.println("Frase criptografada: \n" + frase);
        linhaDivisoria();

        // chama o método de descriptografar
        String fraseNova = descriptografar(frase, deslocamento);
        System.out.printf("A frase descriptografada do Thomaz é:\n%s\n", fraseNova);

        // teste para verificar se a criptografia bate com a frase dada
        /*
         * String teste =
         * "omeucompiladorestasempredizendoquemeucodigoestabaixandoonivel";
         * String testeteste = criptografar(teste, deslocamento);
         * System.out.println(testeteste);
         */
        linhaDivisoria();
        int matricula2 = 23102625;
        System.out.println("PEDRO:");
        System.out.printf("Matricula: %d \n", matricula2);
        int digito2 = 3;
        System.out.printf("Dígito verificador: %d \n", digito2);
        // chama o método de calcular o deslocamento
        int deslocamento2 = calculaDeslocamento(matricula2, digito2);
        System.out.printf("O cálculo do deslocamento resulta em: %d. \n", deslocamento2);
        // frase criptografada pedro
        /*
         * 0x64, 0x71, 0x62, 0x65, 0x77, 0x79, 0x73, 0x71, 0x72, 0x79, 0x64, 0x71, 0x68,
         * 0x79, 0x71, 0x71, 0x76, 0x71, 0x63, 0x79, 0x62, 0x79, 0x71, 0x75, 0x6a, 0x6b,
         * 0x74, 0x65, 0x65, 0x6b, 0x64, 0x71, 0x74, 0x71, 0x62, 0x79, 0x6a, 0x75, 0x68,
         * 0x71, 0x62, 0x63, 0x75, 0x64, 0x6a, 0x75
         */
        // transformando em letras do alfabeto através da tabela ASCII
        String frase2 = "dqbewysqrydqhyqqvqcybyqujkteekdqtqbyjuhqbcudju";
        System.out.println("Frase criptografada: \n" + frase2);
        linhaDivisoria();

        // chama o método de descriptografar
        String fraseNova2 = descriptografar(frase2, deslocamento2);
        System.out.printf("A frase descriptografada do Pedro é:\n%s\n", fraseNova2);
    }
}
