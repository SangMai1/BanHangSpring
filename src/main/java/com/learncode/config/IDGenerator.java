package com.learncode.config;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Properties;
public class IDGenerator implements IdentifierGenerator, Configurable {
    private static long starter = 1596092909841l;
    private static long server = 100000;// tối đa 90 server, từ 100000 đến 990000
    private static long counter = 0;
    private static long realStarter=0;
    private static HashMap<Long,Long> test = new HashMap<>();
    public static synchronized long get(){
        if(counter>9999){
            try {
                Thread.sleep(1);
            } catch (Exception e) {
            }
            counter=0;
        }
        if(counter==0){
            realStarter=((System.currentTimeMillis()-starter) * 1000000);
        }
        return realStarter + server + counter++;
    }
    // for Entity
    @Override
    public Serializable generate(
            SharedSessionContractImplementor session, Object obj)
            throws HibernateException {
        return IDGenerator.get();
    }
    @Override
	public void configure(org.hibernate.type.Type type, Properties properties, ServiceRegistry serviceRegistry)
			throws MappingException {
	}
}