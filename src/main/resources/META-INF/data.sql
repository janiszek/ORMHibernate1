delete from RENTAL_DATES WHERE EXISTS (select 1 from RENTAL_DATES);
delete from RENTAL_ADDRESS WHERE EXISTS (select 1 from RENTAL_DATES);
delete from CAR WHERE EXISTS (select 1 from CAR);
delete from CUSTOMER WHERE EXISTS (select 1 from CUSTOMER);
delete from RENTAL WHERE EXISTS (select 1 from RENTAL);
delete from INVOICE WHERE EXISTS (select 1 from INVOICE);
