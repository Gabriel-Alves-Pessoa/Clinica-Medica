package medica_clinica;

import javafx.application.Platform;
import javafx.scene.control.TextField;

/**
 *
 * @author Rivaldo
 */
public class Mascara {
    
    /*limitarTamanho, apresenta um nome bem sugestivo, e serve para
    limitar o tamanho do valor do campo, sendo usado por outros métodos a seguir.*/
    
    private static void limitarTamanho(TextField textField, int tamanho){
        textField.textProperty().addListener((observable, oldValue, newValue)->{
            if(newValue==null || newValue.length()>tamanho){
                textField.setText(oldValue);
            }
        });
    }
    
    private static void posicionarCursor(TextField textField){
        Platform.runLater(()->{
            if(textField.getText().length()!=0){
                textField.positionCaret(textField.getText().length());
            }
        });
    }
    
    
    public static void maskCpf(TextField textFielf){
        limitarTamanho(textFielf, 14); 
        
        /*limita em 14 o tamanho do cpf
        a linha a baixo escuta o que esta sendo digitado no campo*/
        
        textFielf.lengthProperty().addListener((observable, oldValue, newValue)->{
            String textoDigitado=textFielf.getText(); //pega o valor que foi digitado
            
            textoDigitado=textoDigitado.replaceAll("[^0-9]",""); // Só permite números
            
            textoDigitado=textoDigitado.replaceFirst("(\\d{3})(\\d)", "$1.$2"); //coloca o ponto
            //depois de 3 primeiros dígitos.
            
            textoDigitado=textoDigitado.replaceFirst("(\\d{3})(\\d)", "$1.$2");//coloca outro
            //ponto depois de 3 outros dígitos.
            
            textoDigitado=textoDigitado.replaceFirst("(\\d{3})(\\d)", "$1-$2");// coloca o traço
            //depois de mais 3 dígitos.
            
            textFielf.setText(textoDigitado); //seta o valor no textfield formatado
            
            posicionarCursor(textFielf); //posiciona o cursor no final do valor digitado
        });
    }
    
    
    /* o método abaixo só permite letras e espaços em
    branco. Ele não deixa você digitar um número.*/
    
    public static void maskString(TextField textField){
        limitarTamanho(textField, 150);
        textField.lengthProperty().addListener((observable, oldValue, newValue)->{
            String textoDigitado=textField.getText();
            textoDigitado=textoDigitado.replaceAll("[^A-Za-z\\s]","");
            textField.setText(textoDigitado);
        });
    } 
    
    public static void maskStringOrNumber(TextField textField){
        limitarTamanho(textField, 150);
        textField.lengthProperty().addListener((observable, oldValue, newValue)->{
            String textoDigitado=textField.getText();
            textoDigitado=textoDigitado.replaceAll("[^A-Za-z0-9\\s]","");
            textField.setText(textoDigitado);
        });
    }
    
    public static void maskStringOrNumberNoSpace(TextField textField){
        limitarTamanho(textField, 50);
        textField.lengthProperty().addListener((observable, oldValue, newValue)->{
            String textoDigitado=textField.getText();
            textoDigitado=textoDigitado.replaceAll("[^A-Za-z0-9]","");
            textField.setText(textoDigitado);
        });
    }
    
    public static void maskNumber(TextField textField){
        limitarTamanho(textField, 50);
        textField.lengthProperty().addListener((observable, oldValue, newValue)->{
            String textoDigitado=textField.getText();
            textoDigitado=textoDigitado.replaceAll("[^0-9]","");
            textField.setText(textoDigitado);
        });
    }
    
    public static void maskRg(TextField textField){
        limitarTamanho(textField, 10);
        textField.lengthProperty().addListener((observable, oldValue, newValue)->{
            String textoDigitado=textField.getText();
            textoDigitado=textoDigitado.replaceAll("[^0-9]","");
            textoDigitado=textoDigitado.replaceFirst("(\\d{8})(\\d)", "$1-$2");
            textField.setText(textoDigitado);
        });
    }
    
    public static void maskCrm(TextField textField){
        limitarTamanho(textField, 8);
        textField.lengthProperty().addListener((observable, oldValue, newValue)->{
            String textoDigitado=textField.getText();
            textoDigitado=textoDigitado.replaceAll("[^0-9A-Z]","");
            textoDigitado=textoDigitado.replaceFirst("(\\w{5})(\\w)", "$1/$2");
            //textoDigitado=textoDigitado.replaceFirst("(\\d{8})\\-(\\d{1})(\\d)", "$1-$2/$3");
            textField.setText(textoDigitado);
        });
    }
    
    public static void maskCnpj(TextField textField){
        limitarTamanho(textField, 18);
        textField.lengthProperty().addListener((observable, oldValue, newValue)->{
            String textoDigitado=textField.getText();
            textoDigitado=textoDigitado.replaceAll("[^0-9]","");
            textoDigitado=textoDigitado.replaceFirst("(\\d{2})(\\d)", "$1.$2");
            textoDigitado=textoDigitado.replaceFirst("(\\d{3})(\\d)", "$1.$2");
            textoDigitado=textoDigitado.replaceFirst("(\\d{3})(\\d)", "$1/$2");
            textoDigitado=textoDigitado.replaceFirst("(\\d{4})(\\d)", "$1-$2");
            textField.setText(textoDigitado);
        });
    }
    
    public static void maskHora(TextField textField){
        limitarTamanho(textField,5);
        textField.lengthProperty().addListener((observable, oldValue, newValue)->{
            String textoDigitado=textField.getText();
            textoDigitado=textoDigitado.replaceAll("[^0-9]","");
            textoDigitado=textoDigitado.replaceFirst("(\\d{2})(\\d)", "$1:$2");
            textField.setText(textoDigitado);
        });
    }
    
    //máscara para cep
    public static void maskCep(TextField textField){
        limitarTamanho(textField, 10);
        textField.lengthProperty().addListener((observable, oldValue, newValue)->{
            String textoDigitado=textField.getText();
            textoDigitado=textoDigitado.replaceAll("[^0-9]","");
            textoDigitado=textoDigitado.replaceFirst("(\\d{2})(\\d)", "$1.$2");
            textoDigitado=textoDigitado.replaceFirst("(\\d{3})(\\d)", "$1-$2");
            textField.setText(textoDigitado);
            posicionarCursor(textField);
        });
    }
    
    
    //Máscara para Data
    public static void maskData(TextField textField){
        limitarTamanho(textField, 10);
        textField.lengthProperty().addListener((observable, oldValue, newValue)->{
            String textoDigitado=textField.getText();
            textoDigitado=textoDigitado.replaceAll("[^0-9]","");
            textoDigitado=textoDigitado.replaceFirst("(\\d{2})(\\d)", "$1/$2");
            textoDigitado=textoDigitado.replaceFirst("(\\d{2})(\\d)", "$1/$2");
            textField.setText(textoDigitado);
            posicionarCursor(textField);
        });
    }
    
    
    //Máscara para dinheiro
    public static void maskDinheiro(TextField textField){
        textField.lengthProperty().addListener((observable, oldValue, newValue)->{
            String textoDigitado=textField.getText();
            textoDigitado=textoDigitado.replaceAll("[^0-9]","");
            textoDigitado=textoDigitado.replaceFirst("(\\d{1})(\\d)", "$1,$2");
            textoDigitado=textoDigitado.replaceFirst("(\\d{3})(\\d)", "$1.$2");
            textoDigitado=textoDigitado.replaceFirst("(\\d{3})(\\d)", "$1.$2");
            textoDigitado=textoDigitado.replaceFirst("(\\d{3})(\\d)", "$1.$2");
            textoDigitado=textoDigitado.replaceFirst("(\\d{3})(\\d)", "$1.$2");
            textoDigitado=textoDigitado.replaceFirst("(\\d{3})(\\d)", "$1.$2");
            textField.setText(textoDigitado);
            posicionarCursor(textField);
        });
    }
}

