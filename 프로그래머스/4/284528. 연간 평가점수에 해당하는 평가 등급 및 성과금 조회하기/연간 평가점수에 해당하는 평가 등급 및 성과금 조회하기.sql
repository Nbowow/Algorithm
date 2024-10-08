-- 코드를 작성해주세요
# 평균 점수 계산
WITH S AS (
    SELECT EMP_NO, AVG(SCORE) AS AVG
    FROM HR_GRADE
    GROUP BY EMP_NO
),
# 등급 계산
G AS (
    SELECT HG.EMP_NO, HE.EMP_NAME,
    CASE
        WHEN S.AVG >= 96 THEN 'S'
        WHEN S.AVG >= 90 THEN 'A'
        WHEN S.AVG >= 80 THEN 'B'
        ELSE 'C'
    END AS GRADE
    FROM S
    JOIN HR_GRADE HG ON  HG.EMP_NO = S.EMP_NO
    JOIN HR_EMPLOYEES HE ON HG.EMP_NO = HE.EMP_NO
    WHERE HG.HALF_YEAR = 1
)

# 답
SELECT G.EMP_NO, G.EMP_NAME, G.GRADE, 
    CASE
        WHEN G.GRADE = 'S' THEN HE.SAL * 0.2
        WHEN G.GRADE = 'A' THEN HE.SAL * 0.15
        WHEN G.GRADE = 'B' THEN HE.SAL * 0.1
        ELSE 0
    END AS BONUS
FROM G
JOIN HR_EMPLOYEES HE ON G.EMP_NO = HE.EMP_NO
ORDER BY G.EMP_NO
    