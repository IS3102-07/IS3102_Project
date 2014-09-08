/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CorporateManagement.FacilityManagement;

import javax.ejb.Local;

/**
 *
 * @author Loi Liang Yang
 */
@Local
public interface FacilityManagementBeanLocal {
    public boolean addRegionalOffice(String regionalOfficeName);
    public boolean removeRegionalOffice(String regionalOfficeName);
    public MemberEntity createManufacturingFacility(String email, String password);

    public boolean removeManufacturingFacility(String email);
    public StaffEntity createStore(String identificationNo, String name, Integer phone, String email, String address, String password);
    public StaffEntity removeStore(String username, String password);
    
    public List<RoleEntity> viewFacilityInfo();
    public RoleEntity searchFacility(String name, String accessLevel);
}
