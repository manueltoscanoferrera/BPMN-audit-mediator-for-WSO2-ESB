import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Iterator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLStreamWriter;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.ds.jaxb.JAXBOMDataSource;
import org.codehaus.jettison.mapped.Configuration;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamWriter;
import org.codigolibre.auditbpmn.jaxb.BusinessProcessAudit;
import org.codigolibre.auditbpmn.jaxb.ObjectFactory;
import org.codigolibre.auditbpmn.jaxb.SendTaskAudit;
import org.codigolibre.auditbpmn.jaxb.TaskAuditType;


public class Pruebas {

	private Object findObjectByID(String id, String className,
			BusinessProcessAudit businessProcessAudit) {

		try{
			if (id == null || businessProcessAudit.getActivities() == null)
				return null;

			for (Iterator iterator = businessProcessAudit.getActivities().iterator(); iterator
					.hasNext();) {
	
				Object activity = iterator.next();
				Method method = activity.getClass().getMethod("getId", null);
				if (method!=null){
				 String idObject = (String) method.invoke(activity, null);
				 	if (idObject.equalsIgnoreCase(id)){
				 		return activity;
				 	}
				}
				
			}
		}catch (Exception e){
				
			e.printStackTrace();
			System.out.println("Error " + e.getMessage());	
			return null;
			}
		return null;
		}
	
	
	
	public static void main(String args[]) {

	
		try {
			ObjectFactory objF = new ObjectFactory();
			
			
			BusinessProcessAudit busines = objF.createBusinessProcessAudit();
			
			
			/*
			 SendTaskAudit sendTask = objF.createSendTaskAudit();
			sendTask.setId("2");
	
			
			
			busines.getActivities().add(objF.createSendTaskAudit(sendTask));
			*/
			
			TaskAuditType task = objF.createTaskAuditType();
			task.setId("dd");
			busines.getActivities().add(objF.createTaskAudit(task));
		
				
			System.out.println("tipo " + busines.getActivities().get(0).getDeclaredType());
			
			if (busines.getActivities().get(0).getValue() instanceof SendTaskAudit){
				SendTaskAudit send2 = 
						(SendTaskAudit) busines.getActivities().get(0).getValue();
				
				System.out.println("Pillada " + send2.getId());
				
			}
			
			
// JAXB to OMElement
			
			
		    
	             JAXBContext context = JAXBContext.newInstance(BusinessProcessAudit.class);

	             OMFactory factory = OMAbstractFactory.getOMFactory();
	             OMElement element = factory.createOMElement(new JAXBOMDataSource(context, busines));
	              
	              System.out.println(" jaxb to element to string " + element.toString());

	              
	// JAXB to JSON              
	              Configuration config = new Configuration();
	              config.setIgnoreNamespaces(true);
   
	              MappedNamespaceConvention con = new MappedNamespaceConvention(config);
	      		  StringWriter writer = new StringWriter();
	              XMLStreamWriter xmlStreamWriter = new MappedXMLStreamWriter(con, writer);
	       
	              Marshaller marshaller = context.createMarshaller();
	              marshaller.marshal(busines, xmlStreamWriter);
	              
	              
	              System.out.println(" json " + writer.toString());
	              /*
	              DocumentBean bean = (DocumentBean)JAXBUtils.unmarshal(context, element, true);
	              assertEquals(orgBean.getId(), bean.getId());
	              assertEquals(orgBean.getContent(), bean.getContent());
		*/
			
			
			// JAXB to XMl
			JAXBContext jaxbContext;

				jaxbContext = JAXBContext.newInstance(BusinessProcessAudit.class);

				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				
		
				StringWriter stringTransaccion = new StringWriter();
				ObjectFactory fac = new ObjectFactory();

	

				jaxbMarshaller.marshal(busines, stringTransaccion);

				System.out.println("busines creada "
						+ stringTransaccion.toString());

	
			

		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
		
		/**
		
		System.out.println(new Integer("10").toString());
		
		System.out.println(((Object)new Integer("10")).toString());
		System.out.println(((Object)"10").toString());
		
		System.out.println(((Object)new Integer("10")).toString());
		
		System.out.println("true " + Boolean.TRUE.toString());
		System.out.println("false " + Boolean.FALSE.toString());

		System.out.println(" "
				+ AuditMediatorUtils.TIPO_TRANSACCION.PROXY.isEquals("PROXY"));

		System.out.println("comillas " + StringUtils.isBlank(""));
		System.out.println("null " + StringUtils.isBlank(null));
		System.out.println(" espacios " + StringUtils.isBlank("  "));
		System.out.println("a " + StringUtils.isBlank("a"));

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Transaccion.class);

			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			StringWriter stringTransaccion = new StringWriter();
			ObjectFactory fac = new ObjectFactory();

			Transaccion transaccionBase = fac.createTransaccion();
			transaccionBase.setDetalleTransaccion(fac
					.createTransaccionDetalleTransaccion());
			transaccionBase.getDetalleTransaccion().setDetalleTransaccionProxy(
					fac.createDetalleTransaccionProxyType());

			jaxbMarshaller.marshal(transaccionBase, stringTransaccion);

			System.out.println("Transaccion creada "
					+ stringTransaccion.toString());

		} catch (Exception e) {

			e.printStackTrace();
		}
**/
	}
}
