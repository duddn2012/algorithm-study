SELECT USER_ID, NICKNAME, CONCAT(A.CITY,' ', A.STREET_ADDRESS1,' ', A.STREET_ADDRESS2) AS 전체주소, CONCAT(LEFT(TLNO,3),'-',MID(TLNO,4,4),'-',RIGHT(TLNO,4)) AS 전화번호
FROM USED_GOODS_USER A
JOIN USED_GOODS_BOARD B ON A.USER_ID = B.WRITER_ID
GROUP BY A.USER_ID
HAVING COUNT(B.WRITER_ID) >= 3
ORDER BY A.USER_ID DESC