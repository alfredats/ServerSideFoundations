# Server Side Foundations

### Using ssh to communicate with git
NUS blocks port 22, so update your git remote address as follows:
```ssh://git@ssh.github.com:443/<username>/<reponame>.git```

### Deploying sub-directories to Heroku

```git subtree push --prefix <subdir> <remote> <localbranch>```
Reference: https://www.atlassian.com/git/tutorials/git-subtree
