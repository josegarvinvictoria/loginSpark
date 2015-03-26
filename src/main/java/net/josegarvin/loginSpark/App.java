package net.josegarvin.loginSpark;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.SparkBase.*;

import javax.swing.text.html.HTML;


/**
 * Hello world!
 *
 */
public class App 
{
	static String usuariCorrecte = "cendrassos";
	static String passCorrecte = "1234";
	
    public static void main( String[] args )
    {
    	staticFileLocation("/fitxersHTML");
    	
    	get("/", (peticio, resposta) -> {
    		resposta.redirect("/index.html");
    		
    		return resposta;
    	});
    	
    	
    	
    	
    	post("/validar", (peticio, resposta) -> {
    		String usuariEspecificat = peticio.queryParams("usuari");
    		String clauEspecificada = peticio.queryParams("clau");
    		StringBuilder html = new StringBuilder();
    		
    		if(usuariEspecificat.equals(usuariCorrecte) &&
    				clauEspecificada.equals(passCorrecte)){
    			html.append("<h2>Benvingut usuari " + usuariCorrecte + "<h2>" );
    			return html.toString();
    		}else{
    			resposta.body("Helloooooo!");
    			resposta.redirect("/auth_fail.html");
    			return resposta;
    		}
    		
    	});
    }
}
