/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdr.gomes.infra;

import java.util.Properties;
import org.apache.log4j.Logger;


/**
 *
 * @author 631410128
 */
public class Propriedades {
    
    private static final Logger logger = Logger.getLogger(Propriedades.class);
    private static Properties prop = null;
    
    private Propriedades(){
        prop = new Properties();
        
        try {
            prop.load(this.getClass().getClassLoader().getResourceAsStream("propriedades.properties"));
            logger.debug("Arquivo propriedades.properties lido com sucesso");
            
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        
        
    }
    
    public static Propriedades getInstance() {
        return PropriedadeHolder.INSTANCE;
    }

    private static class PropriedadeHolder {
        private static final Propriedades INSTANCE = new Propriedades();
    }

    public String get(String key) {
        return prop.getProperty(key);
    }
    
}
