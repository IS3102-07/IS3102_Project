/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AnalyticalCRM.CustomerValueAnalysis;

import EntityManager.FurnitureEntity;
import EntityManager.ItemEntity;
import EntityManager.RetailProductEntity;
import EntityManager.SalesRecordEntity;
import java.util.List;

/**
 *
 * @author yang
 */
public interface CustomerValueAnalysisBeanLocal {
    public Integer customerLifetimeValueOfMember(Long memberId);
    public List<ItemEntity> viewSimilarProducts(Long itemId);
    public List<ItemEntity> viewUpsellProducts(Long itemId);
    public Integer viewMonthlyReport();
    public Integer viewSalesSummary();
    public List<FurnitureEntity> viewBestSellingFurniture();
    public List<RetailProductEntity> viewBestSellingRetailProducts();
    public List<SalesRecordEntity> viewMemberSalesRecord(Long memberId);
}
