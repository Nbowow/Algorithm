-- 코드를 작성해주세요
SELECT DISTINCT ID, EMAIL, FIRST_NAME, LAST_NAME
FROM DEVELOPERS D
JOIN SKILLCODES S ON D.SKILL_CODE & S.CODE
WHERE S.CATEGORY = 'FRONT END'
ORDER BY ID ASC


# SELECT *
# FROM DEVELOPERS D
# JOIN SKILLCODES S ON D.SKILL_CODE & S.CODE
# ORDER BY ID ASC