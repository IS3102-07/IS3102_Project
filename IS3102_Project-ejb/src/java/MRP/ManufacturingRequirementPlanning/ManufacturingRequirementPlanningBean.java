/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MRP.ManufacturingRequirementPlanning;

import EntityManager.LineItemEntity;
import EntityManager.ManufacturingFacilityEntity;
import EntityManager.MasterProductionScheduleEntity;
import EntityManager.MaterialRequirementEntity;
import EntityManager.MonthScheduleEntity;
import EntityManager.RawMaterialEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ManufacturingRequirementPlanningBean implements ManufacturingRequirementPlanningBeanLocal {

    @PersistenceContext(unitName = "IS3102_Project-ejbPU")
    private EntityManager em;

    @Override
    public Boolean generateMaterialRequirementPlan(Long MfId) {
        System.out.println("ManufacturingRequirementPlanningBean generateMaterialRequirementPlan is called.");        
        try {
            ManufacturingFacilityEntity mf = em.find(ManufacturingFacilityEntity.class, MfId);
            Query q = em.createQuery("select s from MonthScheduleEntity s");
            List<MonthScheduleEntity> scheduleList = q.getResultList();
            MonthScheduleEntity schedule = scheduleList.get(scheduleList.size() - 1);
            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            calendar.set(Calendar.YEAR, schedule.getYear());
            calendar.set(Calendar.MONTH, schedule.getMonth());

            Query q1 = em.createQuery("select mps from MasterProductionScheduleEntity mps where mps.mf.id = ?1 and mps.schedule.id = ?2")
                    .setParameter(1, MfId)
                    .setParameter(2, schedule.getId());
            List<MasterProductionScheduleEntity> mpsList = (List<MasterProductionScheduleEntity>) q1.getResultList();
            for (MasterProductionScheduleEntity mps : mpsList) {
                for (LineItemEntity lineItem : mps.getFurniture().getBOM().getListOfLineItems()) {
                    Query q2 = em.createQuery("select mr from MaterialRequirementEntity mr where mr.mf.id = ?1 and mr.rawMaterial.SKU = ?2 and mr.schedule.id = ?3 and mr.mps.id = ?4")
                            .setParameter(1, mf.getId())
                            .setParameter(2, lineItem.getItem().getSKU())
                            .setParameter(3, schedule.getId())
                            .setParameter(4, mps.getId());
                    List<MaterialRequirementEntity> mrList = (List<MaterialRequirementEntity>) q2.getResultList();
                    for (MaterialRequirementEntity mr : mrList) {
                        em.remove(mr);
                    }
                    em.flush();

                    MaterialRequirementEntity MR1 = new MaterialRequirementEntity();
                    MR1.setMf(mf);
                    MR1.setMps(mps);
                    MR1.setRawMaterial((RawMaterialEntity) lineItem.getItem());
                    MR1.setQuantity(mps.getAmount_week1() * lineItem.getQuantity());
                    MR1.setSchedule(schedule);
                    MR1.setDay(1);
                    em.persist(MR1);

                    MaterialRequirementEntity MR2 = new MaterialRequirementEntity();
                    MR2.setMf(mf);
                    MR2.setMps(mps);
                    MR2.setRawMaterial((RawMaterialEntity) lineItem.getItem());
                    MR2.setQuantity(mps.getAmount_week2() * lineItem.getQuantity());
                    MR2.setSchedule(schedule);
                    calendar.set(Calendar.WEEK_OF_MONTH, 2);
                    calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
                    System.out.println("calendar.get(Calendar.DAY_OF_MONTH) week2 :" + calendar.get(Calendar.DAY_OF_MONTH));
                    MR2.setDay(calendar.get(Calendar.DAY_OF_MONTH) - 7);
                    em.persist(MR2);

                    MaterialRequirementEntity MR3 = new MaterialRequirementEntity();
                    MR3.setMf(mf);
                    MR3.setMps(mps);
                    MR3.setRawMaterial((RawMaterialEntity) lineItem.getItem());
                    MR3.setQuantity(mps.getAmount_week3() * lineItem.getQuantity());
                    MR3.setSchedule(schedule);
                    calendar.set(Calendar.WEEK_OF_MONTH, 3);
                    calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
                    System.out.println("calendar.get(Calendar.DAY_OF_MONTH) week3 :" + calendar.get(Calendar.DAY_OF_MONTH));
                    MR3.setDay(calendar.get(Calendar.DAY_OF_MONTH) - 7);
                    em.persist(MR3);

                    MaterialRequirementEntity MR4 = new MaterialRequirementEntity();
                    MR4.setMf(mf);
                    MR4.setMps(mps);
                    MR4.setRawMaterial((RawMaterialEntity) lineItem.getItem());
                    MR4.setQuantity(mps.getAmount_week4() * lineItem.getQuantity());
                    MR4.setSchedule(schedule);
                    calendar.set(Calendar.WEEK_OF_MONTH, 4);
                    calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
                    System.out.println("calendar.get(Calendar.DAY_OF_MONTH) week4 :" + calendar.get(Calendar.DAY_OF_MONTH));
                    MR4.setDay(calendar.get(Calendar.DAY_OF_MONTH) - 7);
                    em.persist(MR4);

                    if (mps.getAmount_week5() != 0) {
                        MaterialRequirementEntity MR5 = new MaterialRequirementEntity();
                        MR5.setMf(mf);
                        MR5.setMps(mps);
                        MR5.setRawMaterial((RawMaterialEntity) lineItem.getItem());
                        MR5.setQuantity(mps.getAmount_week5() * lineItem.getQuantity());
                        MR5.setSchedule(schedule);
                        calendar.set(Calendar.WEEK_OF_MONTH, 5);
                        calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
                        System.out.println("calendar.get(Calendar.DAY_OF_MONTH) week5 :" + calendar.get(Calendar.DAY_OF_MONTH));
                        MR5.setDay(calendar.get(Calendar.DAY_OF_MONTH) - 7);
                        em.persist(MR5);
                    }
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<MaterialRequirementEntity> getMaterialRequirementEntityList(Long MfId) {
        try {
            ManufacturingFacilityEntity mf = em.find(ManufacturingFacilityEntity.class, MfId);
            Query q = em.createQuery("select s from MonthScheduleEntity s");
            List<MonthScheduleEntity> scheduleList = q.getResultList();
            MonthScheduleEntity schedule = scheduleList.get(scheduleList.size() - 1);            
            
            Query q1  =em.createQuery("select mr from MaterialRequirementEntity mr where mr.mf.id = ?1 and mr.schedule.id = ?2")
                    .setParameter(1, mf.getId())
                    .setParameter(2, schedule.getId());            
            return (List<MaterialRequirementEntity>)q1.getResultList();            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

}
