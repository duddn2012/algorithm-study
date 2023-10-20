SELECT YEAR(SALES_DATE) YEAR, MONTH(SALES_DATE) MONTH, count(distinct(user_id)) PURCHASED_USERS,round(count(distinct(user_id))/(SELECT count(DISTINCT(user_id)) FROM user_info
                                where joined like '%2021%'),1) as PURCHASED_RATIO
FROM ONLINE_SALE
WHERE USER_ID IN (SELECT DISTINCT(user_id) 
                    FROM USER_INFO
                    WHERE JOINED LIKE '%2021%')
GROUP BY 1,2

ORDER BY 1, 2;