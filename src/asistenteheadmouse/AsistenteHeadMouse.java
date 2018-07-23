/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asistenteheadmouse;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineModeDesc;
import javax.speech.EngineStateError;
import javax.speech.recognition.GrammarException;
import javax.speech.recognition.Recognizer;
import javax.speech.recognition.RuleGrammar;
import voz.Escucha;

/**
 *
 * @author Usuario
 */
public class AsistenteHeadMouse {

    /**
     * @param args the command line arguments
     */
    static Recognizer recognizer;
    public static void main(String[] args) throws IllegalArgumentException, EngineException, GrammarException, EngineStateError, AudioException {
        
        FileReader diccionario = null;
        try {
            diccionario = new FileReader("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\AsistenteHeadMouse\\Diccionario.txt");
            recognizer = Central.createRecognizer(new EngineModeDesc(Locale.ROOT));
           //iniciamos reconocedor
           recognizer.allocate();
           //Estrucutra del archivo a leer
            RuleGrammar rg = recognizer.loadJSGF(diccionario);
            //inicia el archivo a leer
            rg.setEnabled(true);
            recognizer.addResultListener(new Escucha());
            System.out.println("Empieze el dictado....");
            
            recognizer.commitChanges();
            recognizer.requestFocus();
            recognizer.resume();
            
        
        } catch (Exception ex) {
            System.out.println(ex.toString()); 
            ex.printStackTrace();
        }
        
    
    }
}
