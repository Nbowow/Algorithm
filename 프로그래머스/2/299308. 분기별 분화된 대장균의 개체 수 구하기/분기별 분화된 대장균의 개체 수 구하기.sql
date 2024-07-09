-- 코드를 작성해주세요
SELECT 
    CASE
        WHEN MONTH(DIFFERENTIATION_DATE) BETWEEN 1 AND 3 THEN '1Q'
        WHEN MONTH(DIFFERENTIATION_DATE) BETWEEN 4 AND 6 THEN '2Q'
        WHEN MONTH(DIFFERENTIATION_DATE) BETWEEN 7 AND 9 THEN '3Q'
        WHEN MONTH(DIFFERENTIATION_DATE) BETWEEN 10 AND 12 THEN '4Q'
    END AS QUARTER, COUNT(*) AS ECOLI_COUNT
FROM ECOLI_DATA
GROUP BY 
    CASE
        WHEN MONTH(DIFFERENTIATION_DATE) BETWEEN 1 AND 3 THEN '1Q'
        WHEN MONTH(DIFFERENTIATION_DATE) BETWEEN 4 AND 6 THEN '2Q'
        WHEN MONTH(DIFFERENTIATION_DATE) BETWEEN 7 AND 9 THEN '3Q'
        WHEN MONTH(DIFFERENTIATION_DATE) BETWEEN 10 AND 12 THEN '4Q'
    END
ORDER BY 1