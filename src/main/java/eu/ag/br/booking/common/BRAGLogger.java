package eu.ag.br.booking.common;

import org.apache.log4j.Logger;

/**
 * 
 * @author MOwsians
 *
 */
public class BRAGLogger extends Logger {

	public static <T> BRAGLogger newInstance(T clazz) {
		return new BRAGLogger(clazz.toString().split(" ")[1]);
	}
	
	protected BRAGLogger(String name) {
		super(name);
	}

}
