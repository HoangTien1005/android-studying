The repo for my android studying in HCMUS, contains the homework from every week

Usage:
- Clone the repository:
`git clone https://github.com/HoangTien1005/android-studying.git`

- Fetch all the remote branches: 
`cd android-studying && git fetch origin`

- Check to see all the branches available for checkout: 
`git branch -v -a`

- Create local branches tracking remote branches:
``for b in `git branch -r | grep -v -- '->'`; do git branch --track ${b##origin/} $b; done``

- Working with a local branch:
`git checkout <branch-name>`
