SELECT X.FLAVOR
FROM (
SELECT * FROM FIRST_HALF A
UNION
SELECT * FROM JULY A
) X
GROUP BY X.FLAVOR
ORDER BY SUM(X.TOTAL_ORDER) DESC
LIMIT 3