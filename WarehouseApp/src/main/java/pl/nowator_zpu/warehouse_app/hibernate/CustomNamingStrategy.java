package pl.nowator_zpu.warehouse_app.hibernate;

import java.util.List;

import org.hibernate.cfg.reveng.DelegatingReverseEngineeringStrategy; 
import org.hibernate.cfg.reveng.ReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.TableIdentifier;

public class CustomNamingStrategy extends DelegatingReverseEngineeringStrategy {

	public CustomNamingStrategy(ReverseEngineeringStrategy delegate) {
		super(delegate);		 
	}

	@Override
	public String foreignKeyToCollectionName(String keyname, TableIdentifier fromTable, List<?> fromColumns,
			TableIdentifier referencedTable, List<?> referencedColumns, boolean uniqueReference) {
	 
		String foreignKey = super.foreignKeyToCollectionName(keyname, fromTable, fromColumns, referencedTable, referencedColumns,
				uniqueReference);
		
		String collectionName = foreignKey.endsWith("es") ? (foreignKey.substring(0, (foreignKey.length()-2))) : foreignKey;
		
		return collectionName;
		
	}	
}
