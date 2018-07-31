package com.inventory.service.impl;

import com.inventory.domain.entity.Item;
import com.inventory.domain.entity.StockOut;
import com.inventory.domain.enums.StockOutPurpose;
import com.inventory.repository.StockOutRepository;
import com.inventory.service.StockOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import javax.persistence.criteria.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class StockOutServiceImpl implements StockOutService {

    @Autowired
    private StockOutRepository stockOutRepository;

    @Override
    public Long createStockOut(StockOut stockOut) {
        Assert.notNull(stockOut.getItem(), "item not empty");
        Assert.notNull(stockOut.getPickedUser(), "picked user not empty");
        Assert.notNull(stockOut.getApprovedUser(), "approved user not empty");
        Assert.notNull(stockOut.getPurpose(), "purpose not empty");
        Assert.hasLength(stockOut.getNote(), "note not empty");
        StockOut created = stockOutRepository.save(stockOut);

        return created.getId();
    }

    @Override
    public List<StockOut> getAllStockOut() {
        return stockOutRepository.findAll();
    }

    @Override
    public Page<StockOut> getAllStockOut(Pageable pageable) {
        return stockOutRepository.findAll(pageable);
    }

    @Override
    public StockOut updateStockOut(Long stockOutId, StockOut newStockOutInfo) {
        StockOut stockOut = stockOutRepository.findOne(stockOutId);
        Assert.notNull(stockOut, "stockout no exist");
        if (null != newStockOutInfo.getItem()) {
            stockOut.setItem(newStockOutInfo.getItem());
        }
        if (null != newStockOutInfo.getPickedTime()) {
            stockOut.setPickedTime(newStockOutInfo.getPickedTime());
        }
        if (null != newStockOutInfo.getPickedUser()) {
            stockOut.setPickedUser(newStockOutInfo.getPickedUser());
        }
        if (null != newStockOutInfo.getApprovedUser()) {
            stockOut.setApprovedUser(newStockOutInfo.getApprovedUser());
        }
        if (null != newStockOutInfo.getPurpose()) {
            stockOut.setPurpose(newStockOutInfo.getPurpose());
        }
        if (StringUtils.isEmpty(newStockOutInfo.getNote())) {
            stockOut.setNote(newStockOutInfo.getNote());
        }
        StockOut updated = stockOutRepository.save(stockOut);

        return updated;
    }

    @Override
    public StockOut getStockOutDetail(Long stockOutId) {
        Assert.notNull(stockOutId, "stockOutId not null");
        StockOut stockOutDetail = stockOutRepository.findOne(stockOutId);

        return stockOutDetail;
    }

    @Override
    public StockOut getStockOutDetailByItem(Long itemId) {
        Assert.notNull(itemId, "item not null");
        StockOut stockOutDetail = stockOutRepository.findStockOutByItem_SerialId(itemId);

        return stockOutDetail;
    }

    @Override
    public List<StockOut> getStockOutByPickedTime(Timestamp fromTime, Timestamp toTime) {
        Assert.notNull(fromTime, "fromTime not null");
        Assert.notNull(toTime, "toTime not null");
        List<StockOut> stockOutsByPickedTime = stockOutRepository.findStockOutsByPickedTimeBetween(fromTime, toTime);

        return stockOutsByPickedTime;
    }

    @Override
    public Page<StockOut> getStockOutByPickedTime(Pageable pageable, Timestamp fromTime, Timestamp toTime) {
        Assert.notNull(fromTime, "fromTime not null");
        Assert.notNull(toTime, "toTime not null");
        Page<StockOut> stockOutsByPickedTime = stockOutRepository.findStockOutsByPickedTimeBetween(fromTime, toTime, pageable);

        return stockOutsByPickedTime;
    }

    @Override
    public List<StockOut> getStockOutByPurpose(StockOutPurpose purpose) {
        Assert.notNull(purpose, "purpose not null");
        List<StockOut> stockOutsByPurpose = stockOutRepository.findStockOutsByPurpose(purpose);

        return stockOutsByPurpose;
    }

    @Override
    public Page<StockOut> getStockOutByPurpose(Pageable pageable, StockOutPurpose purpose) {
        Assert.notNull(purpose, "purpose not null");
        Page<StockOut> stockOutsByPurpose = stockOutRepository.findStockOutsByPurpose(purpose, pageable);

        return stockOutsByPurpose;
    }

    @Override
    public List<StockOut> getStockOutByPickedUser(Long pickedUserId) {
        Assert.notNull(pickedUserId, "pickedUser Id not null");
        List<StockOut> stockOutsByPickUser = stockOutRepository.findStockOutsByPickedUser(pickedUserId);

        return stockOutsByPickUser;
    }

    @Override
    public Page<StockOut> getStockOutByPickedUser(Pageable pageable, Long pickedUserId) {
        Assert.notNull(pickedUserId, "pickedUser Id not null");
        Page<StockOut> stockOutsByPickUser = stockOutRepository.findStockOutsByPickedUser(pickedUserId, pageable);

        return stockOutsByPickUser;
    }

    @Override
    public List<StockOut> getStockOutByApprovedUser(Long approvedUser) {
        Assert.notNull(approvedUser, "approvedUser Id not null");
        List<StockOut> stockOutsByApprovedUser = stockOutRepository.findStockOutsByApprovedUser(approvedUser);

        return stockOutsByApprovedUser;
    }

    @Override
    public Page<StockOut> getStockOutByApprovedUser(Pageable pageable, Long approvedUser) {
        Assert.notNull(approvedUser, "approvedUser Id not null");
        Page<StockOut> stockOutsByApprovedUser = stockOutRepository.findStockOutsByApprovedUser(approvedUser, pageable);

        return stockOutsByApprovedUser;
    }

    @Override
    public Object getStockOutByCriterion(Pageable pageable, HashMap<String, Object> criterion, String acceptType) {
        Assert.notNull(criterion, "criterion not null");
        Specification querySpec = new Specification<StockOut>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                Join<StockOut, Item> itemJoin = root.join("item", JoinType.LEFT);
                for (Map.Entry<String, Object> entry : criterion.entrySet()) {
                    if (entry.getKey().equals("itemId")) {
                        predicateList.add(criteriaBuilder.equal(itemJoin.get("serialId"), entry.getValue()));
                    } else if (entry.getKey().equals("purpose")) {
                        predicateList.add(criteriaBuilder.equal(root.get(entry.getKey()), StockOutPurpose.valueOf(String.valueOf(entry.getValue()))));
                    } else {
                        predicateList.add(criteriaBuilder.equal(root.get(entry.getKey()), entry.getValue()));
                    }
                }
                Predicate[] predicates = new Predicate[predicateList.size()];

                return criteriaBuilder.and(predicateList.toArray(predicates));
            }
        };

        Object stockOutsByCriterion;
        if (acceptType.equals("list")) {
            stockOutsByCriterion = stockOutRepository.findAll(querySpec);
        } else {
            stockOutsByCriterion = stockOutRepository.findAll(querySpec, pageable);
        }

        return stockOutsByCriterion;
    }
}
