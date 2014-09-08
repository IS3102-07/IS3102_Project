/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CorporateManagement.FacilityManagement;

import EntityManager.ManufacturingFacilityEntity;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Loi Liang Yang
 */
@Stateless
public class FacilityManagementBean implements FacilityManagementBeanLocal {

    @PersistenceContext
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public boolean addRegionalOffice(String regionalOfficeName) {
        System.out.println("registerMember() called with name:" + regionalOfficeName);
        String name;
        Long id;
        try {
            ManufacturingFacilityEntity manufacturingFacilityEntity = new ManufacturingFacilityEntity();
            manufacturingFacilityEntity.create(regionalOfficeName);
            em.persist(manufacturingFacilityEntity);
            name = manufacturingFacilityEntity.getName();
            id = manufacturingFacilityEntity.getId();
            System.out.println("Regional Office Name \"" + name + "\" registered successfully as id:" + id);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to register regional office:\n" + ex);
            return false;
        }
    }

    public boolean removeRegionalOffice(String regionalOfficeName) {
        System.out.println("removeRegionalOffice() called with staffID:" + regionalOfficeName);
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");

            for (Object o : q.getResultList()) {
                ManufacturingFacilityEntity i = (ManufacturingFacilityEntity) o;
                if (i.getName().equalsIgnoreCase(regionalOfficeName)) {
                    em.remove(i);
                    em.flush();
                    System.out.println("\nServer removed regional office:\n" + regionalOfficeName);
                    return true;
                }
            }
            return false; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove regional office:\n" + ex);
            return false;
        }
    }

    public boolean createManufacturingFacility(String email) {
        System.out.println("registerMember() called with name:" + regionalOfficeName);
        String name;
        Long id;
        try {
            ManufacturingFacilityEntity manufacturingFacilityEntity = new ManufacturingFacilityEntity();
            manufacturingFacilityEntity.create(regionalOfficeName);
            em.persist(manufacturingFacilityEntity);
            name = manufacturingFacilityEntity.getName();
            id = manufacturingFacilityEntity.getId();
            System.out.println("Regional Office Name \"" + name + "\" registered successfully as id:" + id);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to register regional office:\n" + ex);
            return false;
        }
    }

    public boolean removeManufacturingFacility(String email) {

    }

    public StaffEntity createStore(String identificationNo, String name, Integer phone, String email, String address, String password) {

    }

    public StaffEntity removeStore(String username, String password) {

    }

    public List<RoleEntity> viewFacilityInfo() {

    }

    public RoleEntity searchFacility(String name, String accessLevel) {

    }
}
