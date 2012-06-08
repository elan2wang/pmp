/**
 * Author            : Elan
 * Created On        : 2012-5-7 下午02:38:15
 * 
 * Copyright 2012.  All rights reserved. 
 *
 * Revision History
 * 
 *    Date       Modifier       Comments
 * ----------    -------------  --------------------------------------------
 * 
 */
package org.pmp.jms;


import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.log4j.Logger;
import org.pmp.util.SpringContextUtil;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class JmsPublisher {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(JmsPublisher.class.getName());
    //~ Instance Fields ================================================================================================
    private static JmsTemplate template =
	(JmsTemplate)SpringContextUtil.getBean("jmsTemplate");
    private static Destination destination = 
	(Destination) SpringContextUtil.getBean("destination");
    
    //~ Methods ========================================================================================================
    public static void sendMessgae(final String ids){
	template.send(destination, new MessageCreator() {  
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("begin to send : "+ids);  
            }  
        }); 
	logger.debug("successfully send a message");
    }
    //~ Getters and Setters ============================================================================================
}
