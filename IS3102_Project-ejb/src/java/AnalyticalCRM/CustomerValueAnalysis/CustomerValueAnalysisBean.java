package AnalyticalCRM.CustomerValueAnalysis;

import EntityManager.FurnitureEntity;
import EntityManager.ItemEntity;
import EntityManager.RetailProductEntity;
import EntityManager.SalesRecordEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CustomerValueAnalysisBean implements CustomerValueAnalysisBeanLocal {

    @PersistenceContext(unitName = "IS3102_Project-ejbPU")
    private EntityManager em;

    @Override
    public Integer customerLifetimeValueOfMember(Long memberId) {
        return 5;
    }

    @Override
    public List<ItemEntity> viewSimilarProducts(Long itemId) {

        List<ItemEntity> similarProducts = new ArrayList();
        return similarProducts;
    }

    @Override
    public List<ItemEntity> viewUpsellProducts(Long itemId) {

        List<ItemEntity> similarProducts = new ArrayList();
        return similarProducts;
    }

    @Override
    public Integer viewMonthlyReport() {

        return 5;
    }

    @Override
    public Integer viewSalesSummary() {

        return 5;
    }

    @Override
    public List<FurnitureEntity> viewBestSellingFurniture() {
        List<FurnitureEntity> bestFurniture = new ArrayList();
        return bestFurniture;
    }

    @Override
    public List<RetailProductEntity> viewBestSellingRetailProducts() {
        List<RetailProductEntity> bestRetailProducts = new ArrayList();
        return bestRetailProducts;
    }

    @Override
    public List<SalesRecordEntity> viewMemberSalesRecord(Long memberId) {
        List<SalesRecordEntity> salesRecords = new ArrayList();
        return salesRecords;
    }

}
