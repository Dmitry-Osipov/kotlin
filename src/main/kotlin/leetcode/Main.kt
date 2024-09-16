package leetcode

fun main() {
    twoSum()
    println("-----------------------------------------------------------")
    preorderTraversal()
    println("-----------------------------------------------------------")
    isPalindrome()
    println("-----------------------------------------------------------")
    longestCommonPrefix()
    println("-----------------------------------------------------------")
    isValid()
    println("-----------------------------------------------------------")
    mergeTwoLists()
    println("-----------------------------------------------------------")
    removeDuplicates()
    println("-----------------------------------------------------------")
    removeElement()
    println("-----------------------------------------------------------")
    strStr()
    println("-----------------------------------------------------------")
    searchInsert()
    println("-----------------------------------------------------------")
    lengthOfLastWord()
    println("-----------------------------------------------------------")
    plusOne()
    println("-----------------------------------------------------------")
    addBinary()
    println("-----------------------------------------------------------")
    deleteDuplicates()
    println("-----------------------------------------------------------")
    inorderTraversal()
    println("-----------------------------------------------------------")
    isSameTree1()
    isSameTree2()
    println("-----------------------------------------------------------")
    isSymmetric()
    println("-----------------------------------------------------------")
    maxDepth()
    println("-----------------------------------------------------------")
    sortedArrayToBST()
    println("-----------------------------------------------------------")
    isBalanced()
    println("-----------------------------------------------------------")
    minDepth()
    println("-----------------------------------------------------------")
    hasCycle1()
    hasCycle2()
    println("-----------------------------------------------------------")
    postorderTraversal()
    println("-----------------------------------------------------------")
    majorityElement()
    println("-----------------------------------------------------------")
}

fun twoSum() {
    val solution = Solution()
    println(solution.twoSum(intArrayOf(2, 7, 11, 15), 9).contentToString())
    println(solution.twoSum(intArrayOf(3, 2, 4), 6).contentToString())
    println(solution.twoSum(intArrayOf(3, 3), 6).contentToString())
}

fun preorderTraversal() {
    val solution = Solution()
    println(solution.preorderTraversal(null))
    println(solution.preorderTraversal(TreeNode(1,
        null,
        TreeNode(2,
            TreeNode(3),
            null))))
    println(solution.preorderTraversal(TreeNode(1,
        TreeNode(2,
            TreeNode(4),
            TreeNode(5,
                TreeNode(6),
                TreeNode(7))),
        TreeNode(3,
            null,
            TreeNode(8,
                TreeNode(9),
                null)))))
}

fun isPalindrome() {
    val solution = Solution()
    println(solution.isPalindrome(121))
    println(solution.isPalindrome(-121))
    println(solution.isPalindrome(10))
}

fun longestCommonPrefix() {
    val solution = Solution()
    println(solution.longestCommonPrefix(arrayOf("flower", "flow", "flight")))
    println(solution.longestCommonPrefix(arrayOf("dog", "racecar", "car")))
}

fun isValid() {
    val solution = Solution()
    println(solution.isValid("()"))
    println(solution.isValid("()[]{}"))
    println(solution.isValid("(]"))
    println(solution.isValid("([])"))
}

fun mergeTwoLists() {
    val solution = Solution()
    println(solution.mergeTwoLists(
        ListNode(1, ListNode(2, ListNode(4))),
        ListNode(1, ListNode(3, ListNode(4)))
    ))
    println(solution.mergeTwoLists(null, null))
    println(solution.mergeTwoLists(null, ListNode(0)))
}

fun removeDuplicates() {
    val solution = Solution()
    println(solution.removeDuplicates(intArrayOf(1, 1, 2)))
    println(solution.removeDuplicates(intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)))
}

fun removeElement() {
    val solution = Solution()
    println(solution.removeElement(intArrayOf(3, 2, 2, 3), 3))
    println(solution.removeElement(intArrayOf(0, 1, 2, 2, 3, 0, 4, 2), 2))
}

fun strStr() {
    val solution = Solution()
    println(solution.strStr("sadbatsad", "sad"))
    println(solution.strStr("leetcode", "leeto"))
}

fun searchInsert() {
    val solution = Solution()
    println(solution.searchInsert(intArrayOf(1, 3, 5, 6), 5))
    println(solution.searchInsert(intArrayOf(1, 3, 5, 6), 2))
    println(solution.searchInsert(intArrayOf(1, 3, 5, 6), 7))
}

fun lengthOfLastWord() {
    val solution = Solution()
    println(solution.lengthOfLastWord("Hello World"))
    println(solution.lengthOfLastWord("     fly me     to     the moon     "))
    println(solution.lengthOfLastWord("luffy is still joyboy"))
}

fun plusOne() {
    val solution = Solution()
    println(solution.plusOne(intArrayOf(1, 2, 3)).contentToString())
    println(solution.plusOne(intArrayOf(4, 3, 2, 1)).contentToString())
    println(solution.plusOne(intArrayOf(9)).contentToString())
}

fun addBinary() {
    val solution = Solution()
    println(solution.addBinary("11", "1"))
    println(solution.addBinary("1010", "1011"))
}

fun deleteDuplicates() {
    val solution = Solution()
    println(solution.deleteDuplicates(null))
    println(solution.deleteDuplicates(ListNode(1, ListNode(1, ListNode(2)))))
    println(solution.deleteDuplicates(ListNode(1, ListNode(1, ListNode(1)))))
    println(solution.deleteDuplicates(ListNode(1, ListNode(1, ListNode(2, ListNode(3, ListNode(3)))))))
}

fun inorderTraversal() {
    val solution = Solution()
    println(solution.inorderTraversal(null))
    println(solution.inorderTraversal(TreeNode(1,
        null,
        TreeNode(2,
            TreeNode(3),
            null))))
    println(solution.inorderTraversal(TreeNode(1,
        TreeNode(2,
            TreeNode(4),
            TreeNode(5,
                TreeNode(6),
                TreeNode(7))),
        TreeNode(3,
            null,
            TreeNode(8,
                TreeNode(9),
                null)))))
}

fun isSameTree1() {
    val solution = Solution()
    println(solution.isSameTree1(
        TreeNode(1, TreeNode(2), TreeNode(3)),
        TreeNode(1, TreeNode(2), TreeNode(3))
    ))
    println(solution.isSameTree1(
        TreeNode(1, TreeNode(2), null),
        TreeNode(1, null, TreeNode(2)),
    ))
    println(solution.isSameTree1(
        TreeNode(1, TreeNode(2), TreeNode(3)),
        TreeNode(1, TreeNode(3), TreeNode(2)),
    ))
}

fun isSameTree2() {
    val solution = Solution()
    println(solution.isSameTree2(
        TreeNode(1, TreeNode(2), TreeNode(3)),
        TreeNode(1, TreeNode(2), TreeNode(3))
    ))
    println(solution.isSameTree2(
        TreeNode(1, TreeNode(2), null),
        TreeNode(1, null, TreeNode(2)),
    ))
    println(solution.isSameTree2(
        TreeNode(1, TreeNode(2), TreeNode(3)),
        TreeNode(1, TreeNode(3), TreeNode(2)),
    ))
}

fun isSymmetric() {
    val solution = Solution()
    println(solution.isSymmetric(TreeNode(1,
        TreeNode(2, null, TreeNode(3)),
        TreeNode(2, null, TreeNode(3)))))
    println(solution.isSymmetric(
        TreeNode(1,
        TreeNode(2,
            TreeNode(3),
            TreeNode(4)
        ),
        TreeNode(2,
            TreeNode(4),
            TreeNode(3)
        )
        )
    ))
}

fun maxDepth() {
    val solution = Solution()
    println(solution.maxDepth(
        TreeNode(3,
            TreeNode(9),
            TreeNode(20,
                TreeNode(15),
                TreeNode(7))))
    )
    println(solution.maxDepth(TreeNode(1, null, TreeNode(2))))
}

fun sortedArrayToBST() {
    val solution = Solution()
    println(solution.sortedArrayToBST(intArrayOf(-10, -3, 0, 5, 9)))
    println(solution.sortedArrayToBST(intArrayOf(1, 3)))
}

fun isBalanced() {
    val solution = Solution()
    println(solution.isBalanced(null))
    println(solution.isBalanced(
        TreeNode(3,
        TreeNode(9),
        TreeNode(20,
            TreeNode(15),
            TreeNode(7)))
    ))
    println(solution.isBalanced(
        TreeNode(1,
        TreeNode(2,
            TreeNode(3,
                TreeNode(4),
                TreeNode(4)),
            TreeNode(3)),
        TreeNode(2))
    ))
    println(solution.isBalanced(
        TreeNode(1,
        TreeNode(2,
            TreeNode(3,
                TreeNode(4),
                null
            ),
            null
        ),
        TreeNode(2,
            null,
            TreeNode(3,
                null,
                TreeNode(4)
            )
        )
        )
    ))
}

fun minDepth() {
    val solution = Solution()
    println(solution.minDepth(
        TreeNode(3,
        TreeNode(9),
        TreeNode(20,
            TreeNode(15),
            TreeNode(7)
        )
        )
    ))
    println(solution.minDepth(TreeNode(1, null, TreeNode(2))))
    println(solution.minDepth(TreeNode(2,
        null,
        TreeNode(3,
            null,
            TreeNode(4,
                null,
                TreeNode(5,
                    null,
                    TreeNode(6)))))))
}

fun hasCycle1() {
    val solution = Solution()
    println(solution.hasCycle1(ListNode(1)))
}

fun hasCycle2() {
    val solution = Solution()
    println(solution.hasCycle2(ListNode(1)))
}

fun postorderTraversal() {
    val solution = Solution()
    println(solution.postorderTraversal(
        TreeNode(1,
            null,
            TreeNode(2,
                TreeNode(3),
                null)))
    )
    println(solution.postorderTraversal(TreeNode(1,
        TreeNode(2,
            TreeNode(4),
            TreeNode(5,
                TreeNode(6),
                TreeNode(7))),
        TreeNode(3,
            null,
            TreeNode(8,
                TreeNode(9),
            null)))))
}

fun majorityElement() {
    val solution = Solution()
    println(solution.majorityElement(intArrayOf(3, 2, 3)))
    println(solution.majorityElement(intArrayOf(2, 2, 1, 1, 1, 2, 2)))
}
