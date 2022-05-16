package com.solvd.internet_store.executors.products;

import com.solvd.internet_store.dao.jdbc.ProductDao;
import com.solvd.internet_store.models.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Executor {
    private static final Logger LOGGER = LogManager.getLogger(Executor.class);

    public static void main(String[] args) {
        ProductDao productDao = new ProductDao();
        productDao.showProducts();
//        Product product = new Product();
//        product.setType("phone");
//        createProduct(productDao, product);
        updateAndDeleteProduct(productDao, 3);
    }

    public static void createProduct(ProductDao productDao, Product product){
        productDao.createEntity(product);
        productDao.showProducts();
    }

    public static void updateAndDeleteProduct(ProductDao productDao, long prodId){
        Product product = productDao.getEntity(prodId);
        LOGGER.info(product);
        product.setType("Nokia");
        productDao.updateEntity(product);
        LOGGER.info(product);
        productDao.showProducts();
        productDao.deleteEntity(product);
        productDao.showProducts();
    }
}
