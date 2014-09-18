package EntityManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class StoreEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToOne
    private WarehouseEntity warehouse;
    @OneToMany(cascade={CascadeType.REMOVE}, mappedBy="store")
    private List<SaleForcastEntity> saleForcastList;    
    @OneToMany(cascade={CascadeType.REMOVE}, mappedBy="store")
    private List<SaleAndOperationPlanEntity> saleAndOperationPlanList;    
    @ManyToMany(mappedBy="storeList")
    @JoinTable(name="store_manufacturingFacility")
    private List<ManufacturingFacilityEntity> manufacturingFacilityList;
    @ManyToOne
    private RegionalOfficeEntity regionalOffice;
    
    public StoreEntity(){
        this.manufacturingFacilityList = new ArrayList<>();
        this.saleForcastList = new ArrayList<>();
        this.saleAndOperationPlanList = new ArrayList<>();
    }
    
    public void create(String name) {
        this.setName(name);
    }    

    public List<SaleAndOperationPlanEntity> getSaleAndOperationPlanList() {
        return saleAndOperationPlanList;
    }

    public void setSaleAndOperationPlanList(List<SaleAndOperationPlanEntity> saleAndOperationPlanList) {
        this.saleAndOperationPlanList = saleAndOperationPlanList;
    }        

    public RegionalOfficeEntity getRegionalOffice() {
        return regionalOffice;
    }

    public void setRegionalOffice(RegionalOfficeEntity regionalOffice) {
        this.regionalOffice = regionalOffice;
    }        

    public List<SaleForcastEntity> getSaleForcastList() {
        return saleForcastList;
    }

    public void setSaleForcastList(List<SaleForcastEntity> saleForcastList) {
        this.saleForcastList = saleForcastList;
    }        
    
    public List<ManufacturingFacilityEntity> getManufacturingFacilityList() {
        return manufacturingFacilityList;
    }

    public void setManufacturingFacilityList(List<ManufacturingFacilityEntity> manufacturingFacilityList) {
        this.manufacturingFacilityList = manufacturingFacilityList;
    }        
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WarehouseEntity getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(WarehouseEntity warehouse) {
        this.warehouse = warehouse;
    }
    
    
}
