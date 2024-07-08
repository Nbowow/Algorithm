-- 코드를 작성해주세요
WITH G1 AS (
    SELECT *
    FROM ECOLI_DATA
    WHERE PARENT_ID IS NULL
),
G2 AS (
    SELECT ED.ID, ED.PARENT_ID
    FROM G1
    JOIN ECOLI_DATA ED ON G1.ID = ED.PARENT_ID
)

SELECT ED.ID
FROM ECOLI_DATA ED
JOIN G2 ON ED.PARENT_ID = G2.ID