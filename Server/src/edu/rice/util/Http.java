package edu.rice.util;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import edu.rice.model.Course;

/*
http://courses.rice.edu/admweb/%21SWKSECX.main?term=201220&amp;title=&amp;course=&amp;crn=&amp;coll=&amp;dept=&amp;subj=LING
*/

public final class Http {
	
	public static final Http Singleton = new Http();
	private static final String DEFAULT_TERM = "201810";
	
	
	private Http() {
		
	}

	@XmlRootElement
	@XmlAccessorType(XmlAccessType.FIELD)
	private static class Courses {
	    @XmlElement(name="course")
	    private Course[] courses;

	}
	
	public List<Course> getCousrses(Map<String, String> map) {
		List<Course> results = new LinkedList<Course>();
		String term = map.getOrDefault("term", DEFAULT_TERM);
		String title = map.getOrDefault("title", "");
		String course = map.getOrDefault("course", "");
		String crn = map.getOrDefault("crn", "");
		String coll = map.getOrDefault("coll", "");
		String dept = map.getOrDefault("dept", "");;
		String subj = map.getOrDefault("subj", "");
		
		String url = "https://courses.rice.edu/admweb/!SWKSECX.main?" + 
				"term=" + term + 
				"&title=" + title +
				"&course=" + course +
				"&crn=" + crn +
				"&coll=" + coll +
				"&dept=" + dept +
				"&subj=" + subj;
		//url = "https://courses.rice.edu/admweb/!SWKSECX.main?term=201810&amp%3btitle=&amp%3bcourse=&amp%3bcrn=&amp%3bcoll=&amp%3bdept=&amp%3bsubj=";
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(Courses.class);
			
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			System.out.println(new URL(url));
			Courses courses = (Courses) unmarshaller.unmarshal(new URL(url));//(new StreamSource( xmlContent));
			if (courses.courses == null)
				return results;
			for (Course c:courses.courses) {
	        	if (c.getInstructor() !=null && c.getInstructor().length() > 50)
	        		c.setInstructor(c.getInstructor().substring(0, 50));
	        	if (c.getStartTime() != null && c.getStartTime().length() > 45)
	        		c.setStartTime(c.getStartTime().substring(0,45));
	        	if (c.getEndTime() != null && c.getEndTime().length() > 45)
	        		c.setEndTime(c.getEndTime().substring(0, 45));
				results.add(c);
			}
		} catch (JAXBException e) {
			e.printStackTrace();
			return results;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return results;
		} catch (Exception e) {
			e.printStackTrace();
			return results;
		}
		return results;
		
 	}
	
	public static void main(String[] args) throws URISyntaxException {
	}
}
