package reply.osmose;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;


	public class SendSituation {
		
	private static final String EXCHANGE_NAME = "OSMOSE";
	//private static final String EXCHANGE_NAME_V_ARICH = "OSMOSE";

	
	
		@SuppressWarnings("rawtypes")
		public static void main(String[] argv)
	                  throws Exception {
	    	
			
	    	 //The queue to read the situation from SA queue in ML
	    	ConnectionFactory factory = new ConnectionFactory();
	    	
	       	factory.setHost("localhost");
	       	Connection connection = factory.newConnection();
	        Channel channel = connection.createChannel();
	        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
	        
	        System.out.println(" [*] Sending message. To exit press CTRL+C");
	        String message = new String("New message");
	        
	        try{
	        	int i = 0;
	     
	            while (i<10) {
	             		channel.basicPublish(EXCHANGE_NAME, "HABITAT_EVENTS.collisions",null, message.getBytes());
	             		i++;	 
	                
	                System.out.println(" [x] Sent '" + message + "'" + i);
	                channel.close();
	    	        connection.close(); 		
	            }
	            }
	        catch(Exception e){e.printStackTrace();}
	        
	        
	        
	    }

	}


