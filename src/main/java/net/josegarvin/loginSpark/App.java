package net.josegarvin.loginSpark;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.SparkBase.*;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

/**
 * Spark example by Jose Garvin Victoria
 *
 */
public class App {
	static String usuariCorrecte = "cendrassos";
	static String passCorrecte = "1234";
	static Map<String, String> map = null;

	public static void main(String[] args) {
		staticFileLocation("/templates");

		get("/", (peticio, resposta) -> {
			resposta.redirect("/index.ftl");

			return resposta;
		});

		post("/validar",
				(peticio, resposta) -> {
					String usuariEspecificat = peticio.queryParams("usuari");
					String clauEspecificada = peticio.queryParams("clau");

					if (usuariEspecificat.equals(usuariCorrecte)
							&& clauEspecificada.equals(passCorrecte)) {
						map = new HashMap<String, String>();
						map.put("usuari", usuariEspecificat);

						resposta.redirect("/auth_ok.ftl");
						return resposta;
					} else {
						resposta.redirect("/auth_fail.ftl");
						return resposta;
					}

				});

		get("/auth_ok.ftl", (rq, rs) -> new ModelAndView(map, "/auth_ok.ftl"),
				new MustacheTemplateEngine());
	}
}
