/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful_service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Jason
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(restful_service.BillOfMaterialEntityFacadeREST.class);
        resources.add(restful_service.CountryEntityFacadeREST.class);
        resources.add(restful_service.CrossOriginResourceSharingFilter.class);
        resources.add(restful_service.FurnitureEntityFacadeREST.class);
        resources.add(restful_service.ItemEntityFacadeREST.class);
        resources.add(restful_service.LoyaltyTierEntityFacadeREST.class);
        resources.add(restful_service.ManufacturingFacilityEntityFacadeREST.class);
        resources.add(restful_service.MemberEntityFacadeREST.class);
        resources.add(restful_service.RegionalOfficeEntityFacadeREST.class);
        resources.add(restful_service.RetailProductEntityFacadeREST.class);
        resources.add(restful_service.ShoppingListEntityFacadeREST.class);
        resources.add(restful_service.StoreEntityFacadeREST.class);
        resources.add(restful_service.WarehouseEntityFacadeREST.class);
    }
    
}
