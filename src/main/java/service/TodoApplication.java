package service;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration.Dynamic;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import resource.TodoResource;

public class TodoApplication extends Application<Configuration> {

	public static void main(String[] args) throws Exception {
		new TodoApplication().run(new String[] { "server", "src/main/resources/config.yaml" });
	}

	@Override
	public void run(Configuration configuration, Environment environment) throws Exception {
		environment.jersey().register(new TodoResource());
		addCorsHeader(environment);
	}

	private void addCorsHeader(Environment environment) {
		Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
		filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
		filter.setInitParameter("allowedOrigins", "*");
		filter.setInitParameter("allowedOrigins", "GET,PUT,POST,DELETE,OPTIONS,HEAD,PATCH");
	}

}