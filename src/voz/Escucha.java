package voz;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.speech.*;
import javax.speech.recognition.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

public class Escucha extends ResultAdapter {

    static Recognizer recognizer;
    String palabra = "";

    @Override
    public void resultAccepted(ResultEvent re) {
        try {
            Result res = (Result) (re.getSource());
            ResultToken tokens[] = res.getBestTokens();
            Robot robot = new Robot();
                
            String args[] = new String[1];
            args[0] = "";
            for (int i = 0; i < tokens.length; i++) {
                palabra = tokens[i].getSpokenText();
                args[0] += palabra + " ";
                System.out.println(palabra + " ");
                
            }
            String resp[] = args[0].split(" ");
            //System.out.println("0 "+resp[0]);
           // System.out.println("1 "+resp[1]);

            if (resp[0].equals("abrir")) {
                accionAbrir(resp[1]);
            }
            if (resp[0].equals("cerrar")) {
                accionCerrar(resp[1]);
            }
            if (resp[0].equals("cero")) {
                robot.keyRelease(KeyEvent.VK_0);
                robot.keyPress(KeyEvent.VK_0);
            }
            if (resp[0].equals("uno")) {
                robot.keyRelease(KeyEvent.VK_1);
                robot.keyPress(KeyEvent.VK_1);
            }
            if (resp[0].equals("dos")) {
                robot.keyRelease(KeyEvent.VK_2);
                robot.keyPress(KeyEvent.VK_2);
            }
            if (resp[0].equals("tres")) {
                robot.keyRelease(KeyEvent.VK_3);
                robot.keyPress(KeyEvent.VK_3);
            }
            if (resp[0].equals("cuatro")) {
                robot.keyRelease(KeyEvent.VK_4);
                robot.keyPress(KeyEvent.VK_4);
            }
            if (resp[0].equals("cinco")) {
                robot.keyRelease(KeyEvent.VK_5);
                robot.keyPress(KeyEvent.VK_5);
            }
            if (resp[0].equals("seis")) {
                robot.keyRelease(KeyEvent.VK_6);
                robot.keyPress(KeyEvent.VK_6);
            }
            if (resp[0].equals("siete")) {
                robot.keyRelease(KeyEvent.VK_7);
                robot.keyPress(KeyEvent.VK_7);
            }
            if (resp[0].equals("ocho")) {
                robot.keyRelease(KeyEvent.VK_8);
                robot.keyPress(KeyEvent.VK_8);
            }
            if (resp[0].equals("nueve")) {
                robot.keyRelease(KeyEvent.VK_9);
                robot.keyPress(KeyEvent.VK_9);
            }
            if (resp[0].equals("borrar")) {
                robot.keyRelease(KeyEvent.VK_BACK_SPACE);
                robot.keyPress(KeyEvent.VK_BACK_SPACE);
            }             
                         
            if (resp[0].equals("escribir")) {
                String escr[] = new String[resp.length - 1];
                 for (int i = 1; i < resp.length; i++) {
                escr[i - 1] = resp[i];
            }
            escribirTexto(escr);
            }             
            if (palabra.equals("entrar")) {
                robot = new Robot();
                robot.keyPress(KeyEvent.VK_ENTER);
            }
            
            
            else {
                recognizer.suspend();
                Leer.main(args);
                recognizer.resume();
            }
        } catch (Exception ex) {
            System.out.println("Ha ocurrido algo inesperado " + ex);
        }
    }

    public static void main(String args[]) {
        try {
            recognizer = Central.createRecognizer(new EngineModeDesc(Locale.ROOT));
            recognizer.allocate();

            FileReader grammar1 = new FileReader("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\AsistenteHeadMouse\\Diccionario.txt");

            RuleGrammar rg = recognizer.loadJSGF(grammar1);
            rg.setEnabled(true);

            recognizer.addResultListener(new Escucha());

            System.out.println("Empieze Dictado");
            recognizer.commitChanges();

            recognizer.requestFocus();
            recognizer.resume();
        } catch (Exception e) {
            System.out.println("Exception en " + e.toString());
            e.printStackTrace();
            System.exit(0);
        }
    }
    
    
    
        public static void accionAbrir(String prog) throws IOException{
       String accion = "";
       Process p = null;
        switch (prog) {
            case "word":
                 accion = "winword";
                p = Runtime.getRuntime().exec("cmd.exe /c start " + accion);
                break;
            case "excel":
                 accion = "excel";
                 p = Runtime.getRuntime().exec("cmd.exe /c start " + accion);
                break;
            case "navegador":
                 accion = "firefox";
                p = Runtime.getRuntime().exec("cmd.exe /c start " + accion);
                break;
//            case "documentos":
//                 accion = "firefox";
//                p = Runtime.getRuntime().exec("cmd.exe /c start " + accion);
//                break;
                
                
            default:
                System.out.println("Ninguna accion realizada");
        }
    }
    public static void accionCerrar(String prog) throws IOException{
        String accion = "";
       Process p = null;
        switch (prog) {
            case "word":
                 accion = "winword";
                p = Runtime.getRuntime().exec("cmd.exe /c taskkill /IM " + accion + ".exe");
                break;
            case "excel":
                 accion = "excel";
                 p = Runtime.getRuntime().exec("cmd.exe /c taskkill /IM " + accion + ".exe");
                break;
            case "navegador":
                 accion = "firefox";
                p = Runtime.getRuntime().exec("cmd.exe /c taskkill /IM " + accion + ".exe");
                break;
//            case "documentos":
//                 accion = "firefox";
//                p = Runtime.getRuntime().exec("cmd.exe /c taskkill /IM " + accion + ".exe");
//                break;
                
                
            default:
                System.out.println("Ninguna accion realizada");
        }
        
    }
    private static void escribirTexto(String buscar[]) throws AWTException {
        StringBuilder escr = new StringBuilder();
        for (int i = 0; i < buscar.length; i++) {
            escr = escr.append(buscar[i].toUpperCase()).append(" ");
        }
       // System.out.println("strBuil..." + escr);
        Robot robot = new Robot();

        for (int i = 0; i < escr.length(); i++) {
            int codletra = escr.codePointAt(i);
            robot.keyPress(codletra);
            robot.keyRelease(codletra);
            //dormimos el robot por 250 mili segundos luego de usar cada tecla
            robot.delay(250);
        }
    }

}

