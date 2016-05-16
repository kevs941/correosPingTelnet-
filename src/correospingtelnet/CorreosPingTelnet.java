/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correospingtelnet;
import java.io.File;
import org.icmp4j.IcmpPingUtil;
import org.icmp4j.IcmpPingRequest;
import org.icmp4j.IcmpPingResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Nataniel
 */
public class CorreosPingTelnet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        //172.217.1.174
        if(verificarPing("192.168.1.220"))
        {
            System.out.println("Si dió respuesta");
            gmail objetoGmail = new gmail(); 
            objetoGmail.username = "0161478@up.edu.mx";
            objetoGmail.password = "AnilloFlor4";
            objetoGmail.to = "0161478@up.edu.mx";
            objetoGmail.filename = "imagen.png";
            objetoGmail.subject = "Archivo de gráfica";
            objetoGmail.body = "Buenas tardes estimado señor. Le mando su amable petición en este correo. ";            
            objetoGmail.sendMailFromObject();
            //enviar correo 
        }
        else{
            System.out.println("No dió respuesta");
            Telnet.doTelnet("64.62.142.154");            
        }
        
        
    
    }
    
    private static boolean verificarPing(String ip) throws Exception{
        final IcmpPingRequest request = IcmpPingUtil.createIcmpPingRequest ();
        request.setHost (ip); 

        // delegate
        final IcmpPingResponse response = IcmpPingUtil.executePingRequest (request);

        // log
        final String formattedResponse = IcmpPingUtil.formatResponse (response);
        System.out.println (formattedResponse);
        //Aquí tomaremos el valor de los ms y los guardaremos en un arreglo. 
        String[] splitStr = formattedResponse.split("\\s+");
        int valor = 0; 
        if(splitStr.length>5)
        {   
            //Quitamos los ms
            char[] cadenaEnChars = splitStr[4].toCharArray();             
            if(cadenaEnChars.length==8){
            valor = Integer.parseInt(Character.toString(cadenaEnChars[5])); 
            }
            else{
                valor = Integer.parseInt(Character.toString(cadenaEnChars[5])+ Character.toString(cadenaEnChars[6]));
            }
            return true;
             
        } 
        else
        {
            //Aquí hacer telnet
            //Aquí enviar correo             
            return false; 
            
        }
                
      
    }
    
    private void mainAnterior() throws Exception{
        //JFreeChart chart; 
        //chart = createPlot();         
        //ChartUtilities.saveChartAsPNG(new File("imagen.png"), chart, 1000, 600);
        
        // request
        final IcmpPingRequest request = IcmpPingUtil.createIcmpPingRequest ();
        request.setHost ("192.168.1.5");

        final XYSeries google = new XYSeries( "Ping a google" );  
        // repeat a few times
        for (int count = 1; count <= 1; count ++) {

        // delegate
        final IcmpPingResponse response = IcmpPingUtil.executePingRequest (request);

        // log
        final String formattedResponse = IcmpPingUtil.formatResponse (response);
        System.out.println (formattedResponse);
        //Aquí tomaremos el valor de los ms y los guardaremos en un arreglo. 
        String[] splitStr = formattedResponse.split("\\s+");
        int valor = 0; 
        if(splitStr.length>5)
        {   
            //Quitamos los ms
            char[] cadenaEnChars = splitStr[4].toCharArray();             
            if(cadenaEnChars.length==8){
            valor = Integer.parseInt(Character.toString(cadenaEnChars[5])); 
            }
            else{
                valor = Integer.parseInt(Character.toString(cadenaEnChars[5])+ Character.toString(cadenaEnChars[6]));
            }
             
        } 
        else
        {
            //Aquí hacer telnet
            //Aquí enviar correo             
            Telnet.doTelnet("64.62.142.154");
            valor = 0; 
        }
                
        google.add( count , valor );                 
        
        

        // rest
        Thread.sleep (1000);
        }
        
        final XYSeriesCollection dataset = new XYSeriesCollection( );          
        dataset.addSeries( google );    
        
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
         "Gráfica de ping" ,
         "Ping #" ,
         "ms" ,
         dataset,
         PlotOrientation.VERTICAL ,
         true , true , false);
        
        ChartUtilities.saveChartAsPNG(new File("imagen.png"), xylineChart, 1000, 600);
    }
}
