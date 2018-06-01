package javax.persistence;

import java.util.List;

public interface EntityManager {

	
	public List<Object> createNativeQuery(String query);
	public List<Object> createQuery(String query);
	
	
}
