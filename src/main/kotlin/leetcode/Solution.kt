package leetcode

import java.math.BigInteger
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

// Простая реализация дерева
class TreeNode(var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null) {
    override fun toString(): String {
        return "TreeNode(val=$`val`, left=$left, right=$right)"
    }
}

// Простая реализация односвязного списка
class ListNode(var `val`: Int, var next: ListNode? = null) {
    override fun toString(): String {
        return "ListNode(val=$`val`, next=$next)"
    }
}

class Solution {
    // https://leetcode.com/problems/two-sum/description/
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = mutableMapOf<Int, Int>()
        for (i in nums.indices) {
            val complement = target - nums[i]
            if (map.containsKey(complement)) {
                return intArrayOf(map.getValue(complement), i)
            }
            map[nums[i]] = i
        }

        return intArrayOf()
    }

    // https://leetcode.com/problems/binary-tree-preorder-traversal/description/
    fun preorderTraversal(root: TreeNode?): List<Int> {
        val list = mutableListOf<Int>()
        val stack = ArrayDeque<TreeNode>()
        root?.let { stack.add(it) } ?: return list
        while (stack.isNotEmpty()) {
            val node = stack.removeLast()
            list.add(node.`val`)
            node.right?.let { stack.add(it) }
            node.left?.let { stack.add(it) }
        }

        return list
    }

    // https://leetcode.com/problems/palindrome-number/description/
    fun isPalindrome(x: Int): Boolean {
        val str = x.toString()
        return str == str.reversed()
    }

    // https://leetcode.com/problems/longest-common-prefix/description/
    fun longestCommonPrefix(strs: Array<String>): String {
        if (strs.isEmpty()) return ""
        var current = strs[0]
        for (i in 0..strs.size - 2) {
            current = current.commonPrefixWith(strs[i + 1])
        }

        return current
    }

    // https://leetcode.com/problems/valid-parentheses/description/
    fun isValid(s: String): Boolean {
        val stack = ArrayDeque<Char>()
        val brackets = mapOf(
            ')' to '(',
            '}' to '{',
            ']' to '[',
        )

        for (c in s) {
            if (c in brackets.values) {
                stack.add(c)
            } else if (c in brackets.keys && (stack.isEmpty() || stack.removeLast() != brackets[c])) {
                return false
            }
        }

        return stack.isEmpty()
    }

    // https://leetcode.com/problems/merge-two-sorted-lists/description/
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        var l1 = list1
        var l2 = list2
        val dummy = ListNode(0)
        var current = dummy

        while (l1 != null && l2 != null) {
            if (l1.`val` < l2.`val`) {
                current.next = l1
                l1 = l1.next
            } else {
                current.next = l2
                l2 = l2.next
            }
            current = current.next!!
        }

        if (l1 != null) {
            current.next = l1
        } else {
            current.next = l2
        }

        return dummy.next
    }

    // https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
    fun removeDuplicates(nums: IntArray): Int {
        val distinct: IntArray = Arrays.stream(nums).distinct().toArray()
        System.arraycopy(distinct, 0, nums, 0, distinct.size)
        return distinct.size
    }

    // https://leetcode.com/problems/remove-element/description/
    fun removeElement(nums: IntArray, `val`: Int): Int {
        val removedArr = Arrays.stream(nums)
            .filter { it != `val` }
            .toArray()!!
        System.arraycopy(removedArr, 0, nums, 0, removedArr.size)
        return removedArr.size
    }

    // https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/description/
    fun strStr(haystack: String, needle: String): Int {
        return haystack.indexOf(needle)
    }

    // https://leetcode.com/problems/search-insert-position/description/
    fun searchInsert(nums: IntArray, target: Int): Int {
        if (nums.isEmpty()) return 0
        var left = 0
        var right = nums.lastIndex
        while (left <= right) {
            val mid = left + (right - left) / 2
            if (nums[mid] == target) return mid
            if (nums[mid] < target) left = mid + 1 else right = mid - 1
        }

        return left
    }

    // https://leetcode.com/problems/length-of-last-word/description/
    fun lengthOfLastWord(s: String): Int {
        val words = s.trim().split("\\s+".toRegex())
        return words[words.lastIndex].length
    }

    // https://leetcode.com/problems/plus-one/description/
    fun plusOne(digits: IntArray): IntArray {
        val sb = StringBuilder()
        for (digit in digits) {
            sb.append(digit)
        }

        var number = BigInteger(sb.toString())
        number = number.plus(BigInteger(1.toString()))
        val temp = number.toString()
        val array = IntArray(temp.length)
        for (i in array.indices) {
            array[i] = temp[i].digitToInt()
        }

        return array
    }

    // https://leetcode.com/problems/add-binary/description/
    fun addBinary(a: String, b: String): String {
        val num1 = a.toBigInteger(2)
        val num2 = b.toBigInteger(2)
        return (num1 + num2).toString(2)
    }

    // https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/
    fun deleteDuplicates(head: ListNode?): ListNode? {
        val dummy = ListNode(0)
        dummy.next = head

        var current = head
        var previous = dummy
        while (current?.next != null) {
            if (current.`val` == current.next!!.`val`) {
                previous.next = current.next
            } else {
                previous = current
            }

            current = current.next
        }

        return dummy.next
    }

    // https://leetcode.com/problems/binary-tree-inorder-traversal/description/
    fun inorderTraversal(root: TreeNode?): List<Int> {
        val list = mutableListOf<Int>()
        helper(root, list)
        return list
    }

    private fun helper(root: TreeNode?, list: MutableList<Int>) {
        if (root != null) {
            helper(root.left, list)
            list.add(root.`val`)
            helper(root.right, list)
        }
    }

    // https://leetcode.com/problems/same-tree/description/
    fun isSameTree1(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null && q == null) {
            return true
        }

        if (p == null || q == null) {
            return false
        }

        return checkTreesByTraverse1(p, q)
    }

    private fun checkTreesByTraverse1(p: TreeNode?, q: TreeNode?): Boolean {
        val sp = ArrayDeque<TreeNode?>()
        sp.add(p)
        val sq = ArrayDeque<TreeNode?>()
        sq.add(q)
        while (sp.isNotEmpty() || sq.isNotEmpty()) {
            val nodeP = if (sp.isNotEmpty()) sp.removeLast() else null
            val nodeQ = if (sq.isNotEmpty()) sq.removeLast() else null
            if (nodeP?.`val` != nodeQ?.`val`) {
                return false
            }

            if (nodeP != null) {
                sp.add(nodeP.left)
                sp.add(nodeP.right)
            }

            if (nodeQ != null) {
                sq.add(nodeQ.left)
                sq.add(nodeQ.right)
            }
        }

        return true
    }

    fun isSameTree2(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null && q == null) return true
        if (p == null || q == null) return false

        val stack = ArrayDeque<Pair<TreeNode?, TreeNode?>>()
        stack.add(p to q)
        while (stack.isNotEmpty()) {
            val (nodeP, nodeQ) = stack.removeLast()
            if (nodeP?.`val` != nodeQ?.`val`) return false
            if (nodeP != null && nodeQ != null) {
                stack.add(nodeP.left to nodeQ.left)
                stack.add(nodeP.right to nodeQ.right)
            }
        }

        return true
    }

    // https://leetcode.com/problems/symmetric-tree/description/
    fun isSymmetric(root: TreeNode?): Boolean {
        if (root == null) return true
        return isMirror(root.left, root.right)
    }

    private fun isMirror(node1: TreeNode?, node2: TreeNode?): Boolean {
        if (node1 == null && node2 == null) return true
        if (node1 == null || node2 == null) return false
        if (node1.`val` != node2.`val`) return false
        return isMirror(node1.left, node2.right) && isMirror(node1.right, node2.left)
    }

    // https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
    fun maxDepth(root: TreeNode?): Int {
        if (root == null) return 0
        val leftDepth = maxDepth(root.left)
        val rightDepth = maxDepth(root.right)
        return max(leftDepth, rightDepth) + 1
    }

    // https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/
    fun sortedArrayToBST(nums: IntArray): TreeNode? {
        return getRootWithBinaryTree(nums, 0, nums.lastIndex)
    }

    private fun getRootWithBinaryTree(arr: IntArray, left: Int, right: Int): TreeNode? {
        if (left <= right) {
            val mid = (left + right) / 2
            val node = TreeNode(arr[mid])
            node.left = getRootWithBinaryTree(arr, left, mid - 1)
            node.right = getRootWithBinaryTree(arr, mid + 1, right)

            return node
        }

        return null
    }

    // https://leetcode.com/problems/balanced-binary-tree/description/
    fun isBalanced(root: TreeNode?): Boolean {
        if (root == null) return true
        return abs(height(root.left) - height(root.right)) < 2 && isBalanced(root.left) && isBalanced(root.right)
    }

    private fun height(root: TreeNode?): Int {
        if (root == null) return -1
        return 1 + max(height(root.left), height(root.right))
    }

    // https://leetcode.com/problems/minimum-depth-of-binary-tree/description/
    fun minDepth(root: TreeNode?): Int {
        return dfs(root)
    }

    private fun dfs(root: TreeNode?): Int {
        if (root == null) return 0
        if (root.left == null) return 1 + dfs(root.right)
        if (root.right == null) return 1 + dfs(root.left)
        return min(dfs(root.left), dfs(root.right)) + 1
    }

    // https://leetcode.com/problems/linked-list-cycle/description/
    fun hasCycle1(head: ListNode?): Boolean {
        if (head == null) return false
        val seen = mutableSetOf<ListNode>()
        var current = head
        while (current != null) {
            if (seen.contains(current)) return true
            seen.add(current)
            current = current.next
        }

        return false
    }

    fun hasCycle2(head: ListNode?): Boolean {
        var fast = head
        var slow = head

        while (fast?.next != null) {
            fast = fast.next!!.next
            slow = slow?.next!!
            if (fast === slow) {
                return true
            }
        }

        return false
    }

    // https://leetcode.com/problems/binary-tree-postorder-traversal/
    fun postorderTraversal(root: TreeNode?): List<Int> {
        val result = LinkedList<Int>()
        if (root == null) return result

        val stack = ArrayDeque<TreeNode>()
        stack.add(root)
        while (stack.isNotEmpty()) {
            val node = stack.removeLast()
            result.addFirst(node.`val`)
            node.left?.let { stack.add(it) }
            node.right?.let { stack.add(it) }
        }

        return result
    }

    //https://leetcode.com/problems/majority-element/description/
    fun majorityElement(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        val map = mutableMapOf<Int, Int>()
        var key: Int? = null
        var value: Int? = null
        var max = 0
        var result = 0;
        for (num in nums) {
            map[num] = map.getOrDefault(num, 0) + 1
            key = map[num]
            value = map[key]
            if (max <= value) {
                max = value
                result = max
            }
        }
    }
}
